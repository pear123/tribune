package com.example.demo.entity;

public class ArticleCustom extends Article {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
