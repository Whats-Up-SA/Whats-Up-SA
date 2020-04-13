package com.codeup.whatsupsa.controllers;


import com.codeup.whatsupsa.Repositories.CategoryRepository;
import com.codeup.whatsupsa.Repositories.EventsRepository;
import com.codeup.whatsupsa.Repositories.InterestedRepository;
import com.codeup.whatsupsa.Repositories.UserRepository;
import com.codeup.whatsupsa.models.*;
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
    private InterestedRepository interestedDao;
    @Value("${filestack.api.key}")
    private String fsapi;

    public EventController(EventsRepository eventDao, UserRepository userDao, CategoryRepository categoryDao, InterestedRepository interestedDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.interestedDao = interestedDao;
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
    public String createPost(@RequestParam String title, @RequestParam String description, @RequestParam Long parentCategory, @RequestParam(name = "eventImage") String eventImage, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String end, @RequestParam String start, @RequestParam String endDateFull, @RequestParam String startDateFull) {
        Event newEvent = new Event();
        List<Category> eventCategories = new ArrayList<>();

        eventCategories.add(categoryDao.getOne(parentCategory));

        newEvent.setCategories(eventCategories);

        newEvent.setTitle(title);
        newEvent.setDescription(description);
        newEvent.setEventImage(eventImage);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        newEvent.setStart(start);
        newEvent.setEnd(end);
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

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Interested interested = interestedDao.checkIfInterested(loggedIn, event);

        model.addAttribute("categories", event.getCategories());
        model.addAttribute("category", event.getCategories().get(0).getCategory());
        model.addAttribute("title", event.getTitle());
        model.addAttribute("description", event.getDescription());
        model.addAttribute("eventImage", event.getEventImage());
        model.addAttribute("startDateFull", event.getStartDateFull());
        model.addAttribute("endDateFull", event.getEndDateFull());
        model.addAttribute("startTime", event.getStartTime());
        model.addAttribute("endTime", event.getEndTime());

        if (interested != null) {
            model.addAttribute("interested", interested.getId());
        }

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
    public String updatePost(@PathVariable Long id, @RequestParam String title, @RequestParam String body, @RequestParam(name = "eventImage") String eventImage, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String end, @RequestParam String start, @RequestParam String endDateFull, @RequestParam String startDateFull) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedIn.getAdmin()) {
            Event e = eventDao.getOne(id);
            e.setTitle(title);
            e.setDescription(body);
            e.setEventImage(eventImage);
            e.setStart(start);
            e.setStartDateFull(startDateFull);
            e.setEnd(end);
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

    //Send all the events as json objects
    @GetMapping("/event/events.json")
    public @ResponseBody
    List<Event> viewAllEventsInJSON() {
        List<Event> events = eventDao.findAll();

        for (Event event : events) {
            String date = event.getStart();
            String date2 = event.getEnd();

            event.setStart(date);
            event.setEnd(date2);
        }

        return events;
    }

    @PostMapping("/events/{id}/interested")
    public String newInterested(@PathVariable Long id) {
        Interested newInterested = new Interested();
        User UserID = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newInterested.setUserID(UserID);
        newInterested.setEvent(eventDao.getOne(id));
        interestedDao.save(newInterested);
        return "redirect:/events/{id}";
    }

    @PostMapping("/events/{id}/decline")
    public String noLongerInterested(@PathVariable Long id) {

        interestedDao.deleteById(id);

        return "redirect:/events/{id}";
    }

}