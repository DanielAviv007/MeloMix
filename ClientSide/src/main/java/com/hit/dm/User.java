package com.hit.dm;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    public String username;
    public Set<Long> playlist;

    public User(String username, Set<Long> playlist) {
        this.username = username;
        this.playlist = playlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Long> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<Long> playlist) {
        this.playlist = playlist;
    }
}
