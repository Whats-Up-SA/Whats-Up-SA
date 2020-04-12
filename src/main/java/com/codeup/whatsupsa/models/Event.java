package com.codeup.whatsupsa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    @JsonIgnore
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isApproved;

    @Column(nullable = true)
    @JsonIgnore
    private String eventImage;

    @Column(nullable = false)
    @JsonIgnore
    private String startTime;

    @Column(nullable = true)
    @JsonIgnore
    private String endTime;

    @Column(nullable = false)
    private String start;

    @Column(nullable = false)
    @JsonIgnore
    private String startDateFull;

    @Column(nullable = true)
    private String end;

    @Column(nullable = true)
    @JsonIgnore
    private String endDateFull;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "event_user",
//            joinColumns = {@JoinColumn(name = "event_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")}
//    )
//
//    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "event_category",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )

    @JsonIgnore
    private List<Category> categories;

    public Event() {
    }

    public Event(Long id, String title, String body, User user, Boolean isApproved, String endTime, String startTime, String start, String end, String startDateFull, String endDateFull) {
        this.title = title;
        this.description = body;
        this.id = id;
        this.user = user;
        this.isApproved = isApproved;
        this.endTime = endTime;
        this.startTime = startTime;
        this.start = start;
        this.end = end;
        this.startDateFull = startDateFull;
        this.endDateFull = endDateFull;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStartDateFull() {
        return startDateFull;
    }

    public void setStartDateFull(String startDateFull) {
        this.startDateFull = startDateFull;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEndDateFull() {
        return endDateFull;
    }

    public void setEndDateFull(String endDateFull) {
        this.endDateFull = endDateFull;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}