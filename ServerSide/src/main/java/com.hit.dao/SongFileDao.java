package com.hit.dao;

import com.google.gson.Gson;
import com.hit.dm.Song;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SongFileDao implements IDao<Long, Song> {
    private final String FILE_PATH;
    private final Gson gson;


    public SongFileDao(String filePath) throws IOException {
        this.FILE_PATH = filePath;
        this.gson = new Gson();

        if (!Files.exists(Paths.get(filePath))) {
            Map<Long, Song> songs = new SongMap();
            String songsJson = this.gson.toJson(songs);

            saveJson(songsJson);
        }
    }

    @Override
    public boolean delete(Long key) throws IOException {
        Map<Long, Song> songsMap = readJson();

        if (!songsMap.containsKey(key))
            return false;

        songsMap.remove(key);

        String songsJson = this.gson.toJson(songsMap);
        saveJson(songsJson);

        return true;
    }

    @Override
    public Song find(Long key) throws IOException {
        return readJson().get(key);
    }

    @Override
    public void save(Song entity) throws IOException {
        Map<Long, Song> songsMap = readJson();

        songsMap.put(entity.getId(), entity);
        String usersJson = this.gson.toJson(songsMap);
        saveJson(usersJson);
    }

    @Override
    public List<Song> readAll() throws IOException {
        Map<Long, Song> songsMap = readJson();


        return new ArrayList<>(songsMap.values());
    }

    private void saveJson(String json) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(json);
        fileWriter.close();
    }

    private Map<Long, Song> readJson() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(FILE_PATH)));

        return gson.fromJson(jsonContent, SongMap.class);
    }
}
