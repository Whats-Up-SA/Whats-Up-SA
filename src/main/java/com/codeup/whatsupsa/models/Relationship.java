package com.codeup.whatsupsa.models;

import javax.persistence.*;

@Entity
@Table(name = "relationship", uniqueConstraints={@UniqueConstraint(columnNames = {"userOneID" , "userTwoID"})})

public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userOneID")
    private User userOneID;

    @ManyToOne
    @JoinColumn(name = "userTwoID")
    private User userTwoID;

    @ManyToOne
    @JoinColumn(name = "actionUserID")
    private User actionUserID;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private long status;

    public Relationship() {
    }

    public Relationship(User userOneID, User userTwoID, User actionUserID, long status) {
        this.userOneID = userOneID;
        this.userTwoID = userTwoID;
        this.actionUserID = actionUserID;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserOneID() {
        return userOneID;
    }

    public void setUserOneID(User userOneID) {
        this.userOneID = userOneID;
    }

    public User getUserTwoID() {
        return userTwoID;
    }

    public void setUserTwoID(User userTwoID) {
        this.userTwoID = userTwoID;
    }

    public User getActionUserID() {
        return actionUserID;
    }

    public void setActionUserID(User actionUserID) {
        this.actionUserID = actionUserID;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
