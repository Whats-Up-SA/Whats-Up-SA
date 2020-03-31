package com.codeup.whatsupsa.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, length = 1000)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_category",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
            )

    private List<Category> categories ;

    public Event() {
    }

    public Event(long id, String title, String body, User user) {
        this.title = title;
        this.description = body;
        this.id = id;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        thisgi.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}