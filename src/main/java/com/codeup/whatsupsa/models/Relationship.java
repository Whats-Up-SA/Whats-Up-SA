package com.codeup.whatsupsa.models;

import javax.persistence.*;
import java.io.Serializable;

public class Relationship implements Serializable {
    private long userOneID;
    private long userTwoID;
    private long actionUserID;
}

@Entity
@IdClass(Relationship.class)
@Table(name = "relationship")

public class Relationship {
    @Id
    private long userOneID;
    @Id
    private long userTwoID;
    @Id
    private long actionUserID;

}
    @ManyToOne
    private User user;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private long status;

//    public Relationship() {
//    }

    public Relationship(long userOneID, long userTwoID, long actionUserID, long status) {
        this.userOneID = userOneID;
        this.userTwoID = userTwoID;
        this.actionUserID = actionUserID;
        this.status = status;
    }

    public long getUserOneID() {
        return userOneID;
    }

    public void setUserOneID(long userOneID) {
        this.userOneID = userOneID;
    }

    public long getUserTwoID() {
        return userTwoID;
    }

    public void setUserTwoID(long userTwoID) {
        this.userTwoID = userTwoID;
    }

    public long getActionUserID() {
        return actionUserID;
    }

    public void setActionUserID(long actionUserID) {
        this.actionUserID = actionUserID;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
