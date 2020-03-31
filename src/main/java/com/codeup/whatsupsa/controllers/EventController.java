package com.codeup.whatsupsa.controllers;


import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.Event;
import com.codeup.whatsupsa.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventController {

    private EventsRepository eventDao;
    private UserRepository userDao;

    public EventController(EventsRepository eventDao, UserRepository userDao){
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    @GetMapping("/submit")
    public String landing() {

        //TODO: logic for checking if a user is logged in goes here
        return "events/submit";
    }

    @PostMapping("/submit")
    public String createPost(@RequestParam String title, @RequestParam String description ){
        Event newEvent = new Event();
        newEvent.setTitle(title);
        newEvent.setDescription(description);
//        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        newEvent.setUser(User);
        newEvent.setApproved(true);
        eventDao.save(newEvent);
        return "redirect:/";
    }



}
