package com.hit.controller;

import com.hit.dm.Song;
import com.hit.service.SongService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongController {
    private final SongService songService;

    public SongController() throws IOException {
        songService = new SongService();
    }

    public void addNewSong(Song song) throws IOException {
        songService.addNewSong(song);
    }

    public List<Song> getAllSongs() throws IOException {
        return songService.getAllSongs();
    }

    public List<Song> getSongsByIds(List<Long> ids) throws IOException {
        List<Song> allSongs = this.songService.getAllSongs();
        List<Song> songs = new ArrayList<>();

        for (Long id : ids)
            for (Song song : allSongs)
                if (id.equals(song.getId()))
                    songs.add(song);

        return songs;
    }

    public List<Song> searchSongsByTitle(String pattern) throws IOException {
        return songService.searchSongsByTitle(pattern);
    }

    public List<Song> searchSongByLyrics(String pattern) throws IOException {
        return songService.searchSongByLyrics(pattern);
    }

    public List<Song> searchSongByArtistName(String pattern) throws IOException {
        return songService.searchSongByArtistName(pattern);
    }
}
