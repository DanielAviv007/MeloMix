package com.hit.dm;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String artistName;
    private String lyrics;
    private final Long id;

    public Song(String title, String artistName, String lyrics) {
        this.title = title;
        this.artistName = artistName;
        this.lyrics = lyrics;

        this.id = generateID();
    }

    private static Long generateID() {
        // Generate a random long value between 1 and 10^9
        long leftLimit = 1L;
        long rightLimit = 1000000000L;

        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Long getId() {
        return id;
    }
}
