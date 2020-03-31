package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @PostMapping("/register")
    @ResponseBody
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
//        model.addAttribute(new User());
        return "users/register";
    }

    @GetMapping("/profile")
    @ResponseBody
    public String profile() {
        return "users/profile";
    }
}

