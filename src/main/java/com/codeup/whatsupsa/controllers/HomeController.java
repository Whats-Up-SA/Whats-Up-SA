package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private EventsRepository eventsDao;

    public HomeController(EventsRepository eventsDao) {
        this.eventsDao = eventsDao;
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
}