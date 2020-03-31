package com.codeup.whatsupsa.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventController {

    @GetMapping("/events/submit")
    public String landing() {

        //TODO: logic for checking if a user is logged in goes here
        return "events/submit";
    }



}
