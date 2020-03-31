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
    private UserRepository usersDao;

    public HomeController(EventsRepository eventsDao, UserRepository usersDao) {
        this.eventsDao = eventsDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/")
    public String getPosts(Model model) {
        model.addAttribute("events", eventsDao.findAll());
        return "/index";
    }
}