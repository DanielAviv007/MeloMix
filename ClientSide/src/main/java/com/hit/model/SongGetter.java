package com.hit.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.Song;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class SongGetter {
    public List<Song> extractSongs(JsonElement jSongs) {
        List<Song> songs = null;
        Gson gson = new Gson();
        Type songListType = new TypeToken<ArrayList<Song>>(){}.getType();

        songs = gson.fromJson(jSongs, songListType);

        return songs;
    }
}
