//package com.codeup.whatsupsa.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "events")
//public class Events {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private long id;
//
////    @Column(nullable = false)
////    private long user_id;
//
//    @Column(nullable = false, length = 1000)
//    private String title;
//
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public Events() {
//    }
//
//    public Events(long id, String title, String description, User user) {
//        this.title = title;
//        this.description = description;
//        this.id = id;
//        this.user = user;
//    }
//
//    public String getTitle() {
//        return this.title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return this.description;
//    }
//
//    public void setBody(String body) {
//        this.description = body;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//}
