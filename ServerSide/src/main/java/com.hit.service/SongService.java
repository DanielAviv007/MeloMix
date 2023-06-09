package com.hit.service;

import com.hit.alg.StringMatcher;
import com.hit.config.Config;
import com.hit.dao.SongFileDao;
import com.hit.dm.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SongService {
    public final SongFileDao songFileDao;
    public final StringMatcher stringMatcher;

    public SongService() throws IOException {
        Config config = Config.getInstance();

        this.songFileDao = new SongFileDao(config.getSongsPath());
        this.stringMatcher = config.getAlgoModule();
    }

    public void addNewSong(Song song) throws IOException {
        songFileDao.save(song);
    }

    public List<Song> getAllSongs() throws IOException {
        return songFileDao.readAll();
    }

    public List<Song> searchSongsByTitle(String pattern) throws IOException {
        return searchSongs(Song -> stringMatcher.contains(Song.getTitle(), pattern));
    }
    public List<Song> searchSongByLyrics(String pattern) throws IOException {
        return searchSongs(Song -> stringMatcher.contains(Song.getLyrics(), pattern));
    }

    public List<Song> searchSongByArtistName(String pattern) throws IOException {
        return searchSongs(Song -> stringMatcher.contains(Song.getArtistName(), pattern));
    }

    private List<Song> searchSongs(Predicate<Song> condition) throws IOException {
        List<Song> matchedSongs = new ArrayList<>();

        for (Song song : songFileDao.readAll())
            if (condition.test(song))
                matchedSongs.add(song);

        return matchedSongs;
    }

}
