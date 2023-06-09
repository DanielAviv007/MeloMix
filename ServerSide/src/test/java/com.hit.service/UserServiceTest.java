package com.hit.service;

import com.hit.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;

public class UserServiceTest {
    private static final String TEST_CONFIG_PATH = "src/test/java/resources/test_config.json";

    @Test
    public void addNewUser_NotExistingUser_SUCCESS() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        UserService userService = new UserService();


        userService.addNewUser("dummy");

        Assertions.assertNotNull(userService.getUser("dummy"));

        File file = new File(config.getUsersPath());
        file.delete();
    }

    @Test
    public void getUser_NotExistingUser_FAILURE() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        UserService userService = new UserService();

        Assertions.assertNull(userService.getUser("dummy_not_exists"));

        File file = new File(config.getUsersPath());
        file.delete();
    }
}
