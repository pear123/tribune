package com.example.demo.entity;

public class UserVo {
    private User user;
    private String rePassword;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "user=" + user +
                ", rePassword='" + rePassword + '\'' +
                '}';
    }

    public UserVo() {
    }
}
