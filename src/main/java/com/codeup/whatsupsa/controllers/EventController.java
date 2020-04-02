package com.codeup.whatsupsa.controllers;


import com.codeup.whatsupsa.Repositories.CategoryRepository;
import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.Category;
import com.codeup.whatsupsa.models.Event;
import com.codeup.whatsupsa.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EventController {

    private EventsRepository eventDao;
    private UserRepository userDao;
    private CategoryRepository categoryDao;

    public EventController(EventsRepository eventDao, UserRepository userDao, CategoryRepository categoryDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/submit")
    public String landing() {

        //TODO: logic for checking if a user is logged in goes here
        return "events/submit";
    }

    @PostMapping("/submit")
    public String createPost(@RequestParam String title, @RequestParam String description) {
        Event newEvent = new Event();
//        model.addAttribute("categories", categoryDao.findAll());
//        List<Category> categoryList = categoryDao.findAll();
//        model.addAttribute("categories", categoryList);
        newEvent.setTitle(title);
        newEvent.setDescription(description);
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newEvent.setUser(loggedIn);
        newEvent.setApproved(true);
        eventDao.save(newEvent);
        return "redirect:/profile";
    }

    @GetMapping("/events/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Event event = eventDao.getOne(id);
//        Category category = categoryDao.getOne(id);
        model.addAttribute("title", event.getTitle());
        model.addAttribute("description", event.getDescription());
//        model.addAttribute("category", category.getCategory());
        return "events/show";
    }

    @GetMapping("/events/{id}/edit")
    public String editEvent(@PathVariable long id, Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Event eventToEdit = eventDao.getOne(id);
        if (loggedIn.getAdmin()) {
            model.addAttribute("event", eventToEdit);
        }
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
//        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Event e = eventDao.getOne(id);
//        if (loggedIn.getIsAdmin()) {
        e.setTitle(title);
        e.setDescription(body);
        eventDao.save(e);
//        }
        return "redirect:/admin";
    }

    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable long id) {
//        System.out.println((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (loggedInUser.getId() == eventDao.getOne(id).getUser().getId())
        // delete post
        eventDao.deleteById(id);

        return "redirect:/admin";
    }
}
