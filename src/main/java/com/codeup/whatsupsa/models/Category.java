package com.codeup.whatsupsa.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private int parent_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event_id;

    @Column(nullable = false, length = 100)
    private String category;

    @ManyToMany(mappedBy = "categories")
    private List<Event> events;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Event> events;

    public Category() {
    }

    public Category(long id, int parent_id, Event event_id, String category){
        this.id = id;
        this.parent_id = parent_id;
        this.event_id = event_id;
        this.category = category;
    }

//    public Categories(Categories copy){
//        id = copy.id;
//        parent_id = copy.parent_id;
//        event_id = copy.event_id;
//        category = copy.category;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public Event getEvents() {
        return event_id;
    }

    public void setEvents(Event events) {
        this.event_id = events;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
