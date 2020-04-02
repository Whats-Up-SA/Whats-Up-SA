package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.User;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventsDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getPosts(Model model) {
        model.addAttribute("events", eventsDao.findAll());
        return "/index";
    }

    @GetMapping("/admin")
    public String getUnapprovedPosts(Model model) {
        model.addAttribute("events", eventsDao.findAll());
        return "/admin";
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
        model.addAttribute("user", userDao.getOne(user.getId()));
        return "users/profile";
    }

//    @GetMapping("/profile/{id}")
//    public String otherProfile(@PathVariable long id, Model model) {
//        model.addAttribute("user", userDao.getOne(id));
//        return "users/otherProfile";
//    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/update";
    }

    @PostMapping("/update")
    public String saveUpdate(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/profile";
    }

}

