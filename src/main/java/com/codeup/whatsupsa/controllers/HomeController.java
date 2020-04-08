package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private EventsRepository eventsDao;
    private UserRepository userDao;

    public HomeController(EventsRepository eventsDao, UserRepository userDao) {
        this.eventsDao = eventsDao;
        this.userDao = userDao;
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

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedIn.getAdmin()) {
            model.addAttribute("events", eventsDao.findAll());
            return "admin";
        } else
            return "redirect:/index";
    }
}