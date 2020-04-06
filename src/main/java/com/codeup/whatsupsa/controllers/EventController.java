package com.codeup.whatsupsa.controllers;


import com.codeup.whatsupsa.Repositories.CategoryRepository;
import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.Category;
import com.codeup.whatsupsa.models.Event;
import com.codeup.whatsupsa.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class EventController {

    private EventsRepository eventDao;
    private UserRepository userDao;
    private CategoryRepository categoryDao;
    @Value("${filestack.api.key}")
    private String fsapi;

    public EventController(EventsRepository eventDao, UserRepository userDao, CategoryRepository categoryDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/submit")
    public String landing(Model model) {
        List<Category> parentCategory = new ArrayList<>();
        List<Category> childCategory = new ArrayList<>();
        List<Category> categoryList = categoryDao.findAll();

        for (Category category : categoryList) {
            if (category.getParent_id() == 0) {
                parentCategory.add(category);
            }
        }
        for (Category category : categoryList) {
            if (category.getParent_id() != 0) {
                childCategory.add(category);
            }
        }
        model.addAttribute("parentCategories", parentCategory);
        model.addAttribute("childCategories", childCategory);
        model.addAttribute("fsapi", fsapi);
        return "events/submit";
    }

    @PostMapping("/submit")
    public String createPost(@RequestParam String title, @RequestParam String description, @RequestParam Long parentCategory, @RequestParam(name = "eventImage") String eventImage) {
        Event newEvent = new Event();
        List<Category> eventCategories = new ArrayList<>();

        eventCategories.add(categoryDao.getOne(parentCategory));

        newEvent.setCategories(eventCategories);

        newEvent.setTitle(title);
        newEvent.setDescription(description);
        newEvent.setEventImage(eventImage);
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newEvent.setUser(loggedIn);
        newEvent.setApproved(false);
        eventDao.save(newEvent);
        return "redirect:/profile";
    }

    @GetMapping("/events/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Event event = eventDao.getOne(id);
        model.addAttribute("categories", event.getCategories());
        model.addAttribute("title", event.getTitle());
        model.addAttribute("description", event.getDescription());
        model.addAttribute("eventImage", event.getEventImage());
        return "events/show";
    }

    @GetMapping("/events/{id}/edit")
    public String editEvent(@PathVariable long id, Model model) {

        List<Category> parentCategory = new ArrayList<>();
        List<Category> categoryList = categoryDao.findAll();

        for (Category category : categoryList) {
            if (category.getParent_id() == 0) {
                parentCategory.add(category);
            }
        }
        model.addAttribute("parentCategories", parentCategory);


        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Event eventToEdit = eventDao.getOne(id);
//        if (loggedIn.getAdmin()) {
        model.addAttribute("event", eventToEdit);
        model.addAttribute("fsapi", fsapi);
//        }
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body, @RequestParam(name = "eventImage") String eventImage) {
//        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Event e = eventDao.getOne(id);
//        if (loggedIn.getIsAdmin()) {
        e.setTitle(title);
        e.setDescription(body);
        e.setEventImage(eventImage);
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

    @PostMapping("/events/{id}/approve")
    public String approvePost(@PathVariable long id) {
//        System.out.println((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (loggedInUser.getId() == eventDao.getOne(id).getUser().getId())
        Event e = eventDao.getOne(id);
        e.setApproved(true);
        eventDao.save(e);
        return "redirect:/index";
    }
}