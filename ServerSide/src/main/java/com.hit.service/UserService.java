package com.hit.service;

import com.hit.config.Config;
import com.hit.dao.UserFileDao;
import com.hit.dm.User;

import java.io.IOException;

public class UserService {
    public final UserFileDao userFileDao;

    public UserService() throws IOException {
        Config config = Config.getInstance();

        this.userFileDao = new UserFileDao(config.getUsersPath());
    }

    public void addNewUser(String username) throws IOException {
        userFileDao.save(new User(username));
    }

    public User getUser(String username) throws IOException {
        return userFileDao.find(username);
    }
}
