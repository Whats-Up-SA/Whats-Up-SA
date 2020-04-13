package com.codeup.whatsupsa.controllers;

import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.Category;
import com.codeup.whatsupsa.models.Event;
import com.codeup.whatsupsa.models.Relationship;
import com.codeup.whatsupsa.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Event> approvedEvents = eventsDao.findApprovedEvents();
        java.util.List<java.util.Map.Entry<Event, Category>> pairList = new java.util.ArrayList<>();
        for (Event event : approvedEvents) {
            Map.Entry<Event, Category> newRequest = new AbstractMap.SimpleEntry<>(event, event.getCategories().get(0));
            pairList.add(newRequest);
        }
        model.addAttribute("pairList", pairList);
        return "index";
    }

    @GetMapping("/admin")
    public String getUnapprovedPosts(Model model) {

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedIn.getAdmin()) {
            List<Event> unapprovedEvents = eventsDao.findPendingEvents();
            java.util.List<java.util.Map.Entry<Event, Category>> pairList = new java.util.ArrayList<>();
            for (Event event : unapprovedEvents) {
                Map.Entry<Event, Category> newRequest = new AbstractMap.SimpleEntry<>(event, event.getCategories().get(0));
                pairList.add(newRequest);
            }
            model.addAttribute("pairList", pairList);
            return "admin";
        } else
            return "redirect:/index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}