package com.hit.dm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private final String username;
    private final Set<Long> playlist;

    public User(String username) {
        this.username = username;
        this.playlist = new HashSet<>();
    }

    public String getUsername() {
        return this.username;
    }

    public Set<Long> getPlaylist() {
        return this.playlist;
    }

    public void addSongToPlaylist(Long songId) {
        playlist.add(songId);
    }

    public void removeSongFromPlaylist(Long songId) {
        playlist.remove(songId);
    }
}
