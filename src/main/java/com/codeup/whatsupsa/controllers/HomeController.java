package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private EventsRepository eventsDao;

    public HomeController(EventsRepository eventsDao) {
        this.eventsDao = eventsDao;
    }

    @GetMapping("/")
    public String hello() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String getPosts(Model model) {
        model.addAttribute("events", eventsDao.findAll());
        return "index";
    }

    @GetMapping("/admin")
    public String getUnapprovedPosts(Model model) {
        model.addAttribute("events", eventsDao.findAll());
        return "/admin";
    }

}