package com.codeup.whatsupsa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/register")
    @ResponseBody
    public String landing() {

        return "This is the placeholder for a registration page!";
    }

    @GetMapping("/profile")
    @ResponseBody
    public String profile() {
        return "this is the profile page";
    }
}

