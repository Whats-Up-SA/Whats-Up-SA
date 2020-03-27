//package com.codeup.whatsupsa.models;
//
//import javax.persistence.Entity;
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//public class User<Post> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private long id;
//
//    @Column(nullable = false, unique = true, length = 255)
//    private String username;
//
//    @Column(nullable = false, unique = true, length = 255)
//    private String email;
//
//    @Column(nullable = false, length = 255)
//    private String password;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Post> posts;
//
//    public User() {
//    }
//
//    public User(String username, String email, String password) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//    }
//
////    public User(User copy) {
////        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
////        email = copy.email;
////        username = copy.username;
////        password = copy.password;
////    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }
//
//}