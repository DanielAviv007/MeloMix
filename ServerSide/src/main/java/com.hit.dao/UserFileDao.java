package com.hit.dao;

import com.google.gson.Gson;
import com.hit.dm.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserFileDao implements IDao<String, User> {
    private final String FILE_PATH;
    private final Gson gson;

    public UserFileDao(String filePath) throws IOException {
        this.FILE_PATH = filePath;
        this.gson = new Gson();

        if (!Files.exists(Paths.get(filePath))) {
            Map<String, User> users = new UserMap();
            String usersJson = this.gson.toJson(users);

            saveJson(usersJson);
        }
    }

    @Override
    public boolean delete(String key) throws IOException {
        Map<String, User> userMap = readJson();

        if (!userMap.containsKey(key))
            return false;

        userMap.remove(key);

        String usersJson = this.gson.toJson(userMap);
        saveJson(usersJson);

        return true;
    }

    @Override
    public User find(String key) throws IOException {
        return readJson().get(key);
    }

    @Override
    public void save(User entity) throws IOException {
        Map<String, User> userMap = readJson();

        userMap.put(entity.getUsername(), entity);
        String usersJson = this.gson.toJson(userMap);
        saveJson(usersJson);
    }

    @Override
    public List<User> readAll() throws IOException {
        Map<String, User> userMap = readJson();

        return new ArrayList<>(userMap.values());
    }

    private void saveJson(String json) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(json);
        fileWriter.close();
    }

    private Map<String, User> readJson() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        return gson.fromJson(jsonContent, UserMap.class);
    }
}
