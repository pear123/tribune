package com.example.demo.entity;

public class CommentCustom extends Comment {
    private User user;
    private User user2;

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
