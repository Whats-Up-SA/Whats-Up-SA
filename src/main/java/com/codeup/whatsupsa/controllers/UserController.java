package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EventsRepository eventDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.eventDao = eventDao;
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

        model.addAttribute("events", eventDao.FindEventsByUserID(user.getId()));
        model.addAttribute("user", userDao.getOne(user.getId()));
        return "users/profile";
    }

    @GetMapping("/profile/{id}")
    public String getPost(@PathVariable long id, Model model) {
        User user = userDao.getOne(id);
        model.addAttribute("user", user.getId());
        return "events/show";
    }

//    @GetMapping("/profile/{id}")
//    public String otherProfile(@PathVariable long id, Model model) {
//        model.addAttribute("user", userDao.getOne(id));
//        return "users/otherProfile";
//    }

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