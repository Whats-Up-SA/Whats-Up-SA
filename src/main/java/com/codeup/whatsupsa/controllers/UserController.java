package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.InterestedRepository;
import com.codeup.whatsupsa.Repositories.RelationshipRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventDao;
    private RelationshipRepository relationshipDao;
    private InterestedRepository interestedDao;

    @Value("${filestack.api.key}")
    private String fsapi;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EventsRepository eventDao, RelationshipRepository relationshipDao, InterestedRepository interestedDao) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.eventDao = eventDao;
        this.relationshipDao = relationshipDao;
        this.interestedDao = interestedDao;
    }

    @GetMapping("/all")
    public String viewAllUsers(Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedIn.getAdmin()) {
            model.addAttribute("users", userDao.findAll());
            return "users/all";
        } else
            return "redirect:/index";
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("fsapi", fsapi);
        model.addAttribute("profileImage", user.getProfileImage());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setAdmin(false);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.getUserById(user1.getId());
        //pending request logic
        List<Relationship> pendingRequests = relationshipDao.viewPendingRequests(user);
        java.util.List<java.util.Map.Entry<String, Long>> pairList = new java.util.ArrayList<>();
        for (Relationship pendingRequest : pendingRequests) {
            Map.Entry<String, Long> newRequest = new AbstractMap.SimpleEntry<>(pendingRequest.getActionUserID().getUsername(), pendingRequest.getId());
            pairList.add(newRequest);
        }
        //friend list logic
        List<User> friendUsers = new ArrayList<>();
        for (Relationship friendPair : relationshipDao.viewFriendsList(user)) {
            if (friendPair.getUserOneID().getId() == user.getId()) {
                friendUsers.add(friendPair.getUserTwoID());
            } else {
                friendUsers.add(friendPair.getUserOneID());
            }
        }

        //event+category logic
        List<Event> approvedEvents = eventDao.FindEventsByUserID(user.getId());
        java.util.List<java.util.Map.Entry<Event, Category>> eventCats = new java.util.ArrayList<>();
        for (Event event : approvedEvents) {
            Map.Entry<Event, Category> newRequest = new AbstractMap.SimpleEntry<>(event, event.getCategories().get(0));
            eventCats.add(newRequest);
        }
        model.addAttribute("eventCats", eventCats);


        model.addAttribute("interestedEvents", interestedDao.findAllByUserID(user));

        model.addAttribute("friendList", friendUsers);
        model.addAttribute("pairList", pairList);
//        model.addAttribute("events", eventDao.FindEventsByUserID(user.getId()));
        model.addAttribute("user", userDao.getOne(user.getId()));
        return "users/profile";
    }

    @GetMapping("/profile/{id}")
    public String otherProfile(@PathVariable long id, Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(user1.getId());

        model.addAttribute("interestedEvents", interestedDao.findAllByUserID(userDao.getOne(id)));

        int checkFriends = 0;
        if (user.getId() < id) {
            if (relationshipDao.getRelationshipByFriends(user, userDao.getOne(id)) == 1) {
                if (relationshipDao.checkPending(user, userDao.getOne(id)) == 1) {
                    checkFriends += 3;
                }
                if (relationshipDao.checkDecline(user, userDao.getOne(id)) == 1) {
                    checkFriends += 2;
                }
                if (relationshipDao.checkFriendship(user, userDao.getOne(id)) == 1) {
                    checkFriends += 1;
                }
                model.addAttribute("relationship", relationshipDao.returnRelationshipFriends(user, userDao.getOne(id)));
            }
            model.addAttribute("checkFriendship", checkFriends);
        } else if (user.getId() > id) {
            if (relationshipDao.getRelationshipByFriends(userDao.getOne(id), user) == 1) {
                if (relationshipDao.checkPending(userDao.getOne(id), user) == 1) {
                    checkFriends += 3;
                }
                if (relationshipDao.checkDecline(userDao.getOne(id), user) == 1) {
                    checkFriends += 2;
                }
                if (relationshipDao.checkFriendship(userDao.getOne(id), user) == 1) {
                    checkFriends += 1;
                }
                model.addAttribute("relationship", relationshipDao.returnRelationshipFriends(userDao.getOne(id), user));
            }
            model.addAttribute("checkFriendship", checkFriends);
        }

        model.addAttribute("user", userDao.getOne(id));
        return "users/detail";
    }

    @PostMapping("/users/detail/fr/{id}")
    public String friendRequest(@PathVariable long id, Model model, Relationship relationship) {
        Relationship newRelationship = new Relationship();
        //checks to see if user is logged in and sets actionUser to them
        User actionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User otherUser = userDao.getOne(id);
        newRelationship.setActionUserID(actionUser);
        //checks which user's id is lower and sets the lower one to user_one_id
        if (actionUser.getId() < otherUser.getId()) {
            newRelationship.setUserOneID(actionUser);
            newRelationship.setUserTwoID(otherUser);
        } else {
            newRelationship.setUserOneID(otherUser);
            newRelationship.setUserTwoID(actionUser);
        }
        //sets relationship status to pending
        newRelationship.setStatus(0);
        //saves relationship in database

        //        Relationship testRelationship = new Relationship(otherUser, actionUser, actionUser, 0);
        relationshipDao.save(newRelationship);

        return "redirect:/profile/{id}";
    }

    @PostMapping("/profile/{id}/approve")
    public String approveFriend(@PathVariable long id) {
        Relationship r = relationshipDao.getOne(id);
        r.setStatus(1);
        r.setActionUserID((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        relationshipDao.save(r);
        return "redirect:/profile";
    }

    @PostMapping("/profile/{id}/decline")
    public String declineFriend(@PathVariable long id) {
        Relationship r = relationshipDao.getOne(id);
        r.setStatus(2);
        r.setActionUserID((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        relationshipDao.save(r);
        return "redirect:/profile";
    }

    @PostMapping("/profile/{id}/fr")
    public String friendRequestFromDenied(@PathVariable long id) {
        Relationship r = relationshipDao.getOne(id);
        r.setStatus(0);
        r.setActionUserID((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        relationshipDao.save(r);
        return "redirect:/profile";
    }

    @PostMapping("/profile/{id}/unfriend")
    public String unfriend(@PathVariable long id) {
        relationshipDao.deleteById(id);
        return "redirect:/profile";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserById(user1.getId());

        model.addAttribute("user", user);
        model.addAttribute("fsapi", fsapi);
        model.addAttribute("profileImage", user.getProfileImage());
        return "users/update";
    }

    @PostMapping("/update")
    public String saveUpdate(@ModelAttribute User user) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String hash = passwordEncoder.encode(user.getPassword());
        user.setId(loggedIn.getId());
        user.setAdmin(loggedIn.getAdmin());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/profile";
    }

    @PostMapping("all/{id}/delete")
    public String deleteUserPage(@PathVariable long id) {
        userDao.deleteById(id);
        return "redirect:/all";
    }

}