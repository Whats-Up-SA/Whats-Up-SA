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
    public String createPost(@RequestParam String title, @RequestParam String description, @RequestParam Long parentCategory, @RequestParam(name = "eventImage") String eventImage, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String endDate, @RequestParam String startDate, @RequestParam String endDateFull, @RequestParam String startDateFull) {
        Event newEvent = new Event();
        List<Category> eventCategories = new ArrayList<>();

        eventCategories.add(categoryDao.getOne(parentCategory));

        newEvent.setCategories(eventCategories);

        newEvent.setTitle(title);
        newEvent.setDescription(description);
        newEvent.setEventImage(eventImage);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        newEvent.setStartDate(startDate);
        newEvent.setEndDate(endDate);
        newEvent.setStartDateFull(startDateFull);
        newEvent.setEndDateFull(endDateFull);
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newEvent.setUser(loggedIn);
        newEvent.setApproved(false);
        eventDao.save(newEvent);
        return "redirect:/profile";
    }

    @GetMapping("/events/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Event event = eventDao.getOne(id);
        model.addAttribute("categories", event.getCategories());
        model.addAttribute("title", event.getTitle());
        model.addAttribute("description", event.getDescription());
        model.addAttribute("eventImage", event.getEventImage());
        return "events/show";
    }

    @GetMapping("/events/{id}/edit")
    public String editEvent(@PathVariable Long id, Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedIn.getAdmin()) {
            List<Category> parentCategory = new ArrayList<>();
            List<Category> categoryList = categoryDao.findAll();

            for (Category category : categoryList) {
                if (category.getParent_id() == 0) {
                    parentCategory.add(category);
                }
            }
            model.addAttribute("parentCategories", parentCategory);
            Event eventToEdit = eventDao.getOne(id);
            model.addAttribute("event", eventToEdit);
            model.addAttribute("fsapi", fsapi);
            model.addAttribute("eventImage", eventToEdit.getEventImage());
            return "events/edit";
        } else
            return "redirect:/index";
    }

    @PostMapping("/events/{id}/edit")
    public String updatePost(@PathVariable Long id, @RequestParam String title, @RequestParam String body, @RequestParam(name = "eventImage") String eventImage, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String endDate, @RequestParam String startDate, @RequestParam String endDateFull, @RequestParam String startDateFull) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedIn.getAdmin()) {
            Event e = eventDao.getOne(id);
            e.setTitle(title);
            e.setDescription(body);
            e.setEventImage(eventImage);
            e.setStartDate(startDate);
            e.setStartDateFull(startDateFull);
            e.setEndDate(endDate);
            e.setEndDateFull(endDateFull);
            e.setStartTime(startTime);
            e.setEndTime(endTime);
            eventDao.save(e);
            return "redirect:/admin";
        } else
            return "redirect:/index";
    }

    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable Long id) {
        eventDao.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/events/{id}/approve")
    public String approvePost(@PathVariable Long id) {
        Event e = eventDao.getOne(id);
        e.setApproved(true);
        eventDao.save(e);
        return "redirect:/index";
    }

}