package com.hit.dm;

import javafx.scene.control.Button;
import java.io.Serializable;

public class Song implements Serializable {
    public String title;
    public String artistName;
    public String lyrics;
    public Long id;

    public Song(String title, String artistName, String lyrics, Long id) {
        this.title = title;
        this.artistName = artistName;
        this.lyrics = lyrics;
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }
}
