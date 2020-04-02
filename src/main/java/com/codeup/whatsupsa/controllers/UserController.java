package com.codeup.whatsupsa.controllers;

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

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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

