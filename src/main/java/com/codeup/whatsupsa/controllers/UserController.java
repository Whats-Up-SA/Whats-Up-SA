package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.RelationshipRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.Relationship;
import com.codeup.whatsupsa.models.User;
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

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EventsRepository eventDao, RelationshipRepository relationshipDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.eventDao = eventDao;
        this.relationshipDao = relationshipDao;
    }

    @GetMapping("/all")
    public String viewAllUsers(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users/all";
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Relationship> pendingRequests = relationshipDao.viewPendingRequests(user);

        java.util.List<java.util.Map.Entry<String,Long>> pairList= new java.util.ArrayList<>();

        for (Relationship pendingRequest : pendingRequests) {
            Map.Entry<String, Long> j = new AbstractMap.SimpleEntry<>(pendingRequest.getActionUserID().getUsername(), pendingRequest.getId());
            pairList.add(j);
        }


        model.addAttribute("pairList",pairList);

//        model.addAttribute("pendingRequests", relationshipDao.viewPendingRequests(user));

        model.addAttribute("events", eventDao.FindEventsByUserID(user.getId()));
        model.addAttribute("user", user);
//        model.addAttribute("user", userDao.getOne(user.getId()));
        return "users/profile";
    }

//    @GetMapping("/profile/{id}")
//    public String getPost(@PathVariable long id, Model model) {
//        User user = userDao.getOne(id);
//        model.addAttribute("user", user.getId());
//        return "events/show";
//    }

    @GetMapping("/profile/{id}")
    public String otherProfile(@PathVariable long id, Model model) {
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
        if (actionUser.getId() < otherUser.getId()){
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

        return "redirect:/index";
    }

    @PostMapping("/profile/{id}/approve")
    public String approveFriend(@PathVariable long id){
        Relationship r = relationshipDao.getOne(id);
        r.setStatus(1);
        r.setActionUserID((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        relationshipDao.save(r);
        return"redirect:/profile";
    }

    @PostMapping("/profile/{id}/decline")
    public String declineFriend(@PathVariable long id){
        Relationship r = relationshipDao.getOne(id);
        r.setStatus(2);
        r.setActionUserID((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        relationshipDao.save(r);
        return"redirect:/profile";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "users/update";
    }

    @PostMapping("/update")
    public String saveUpdate(@ModelAttribute User user) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String hash = passwordEncoder.encode(user.getPassword());
        user.setId(loggedIn.getId());
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