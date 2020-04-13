package com.codeup.whatsupsa.models;

import javax.persistence.*;

@Entity
@Table(name = "Interested", uniqueConstraints = {@UniqueConstraint(columnNames = {"userID", "eventID"})})

public class Interested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userID", foreignKey = @javax.persistence.ForeignKey(name = "none"))
    private User userID;

    @ManyToOne
    @JoinColumn(name = "eventID", foreignKey = @javax.persistence.ForeignKey(name = "none"))
    private Event event;

    public Interested() {
    }

    public Interested(User userID, Event event) {
        this.userID = userID;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}


