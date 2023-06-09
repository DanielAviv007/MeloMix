package com.hit.controller;

import com.hit.dm.User;
import com.hit.service.UserService;

import java.io.IOException;

public class UserController {
    private final UserService userService;

    public UserController() throws IOException {
        userService = new UserService();
    }

    public User login(String username) throws IOException {
        User user = userService.getUser(username);

        if (user == null) {
            user = new User(username);
            userService.addNewUser(username);
        }

        return user;
    }
}
