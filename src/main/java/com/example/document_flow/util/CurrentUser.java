package com.example.document_flow.util;

import com.example.document_flow.entity.User;

public class CurrentUser {

    private static CurrentUser instance;
    private User user;

    private CurrentUser(){}

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public  User getUser() {
        return user;
    }

    public User setUser(User user) {
        this.user = user;
        return this.user;
    }
}
