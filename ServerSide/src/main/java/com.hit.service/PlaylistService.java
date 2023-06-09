package com.hit.service;

import com.hit.config.Config;
import com.hit.dao.UserFileDao;
import com.hit.dm.User;

import java.io.IOException;

public class PlaylistService {
    public final UserFileDao userFileDao;

    public PlaylistService() throws IOException {
        Config config = Config.getInstance();

        userFileDao = new UserFileDao(config.getUsersPath());
    }

    public boolean addSongToPlaylist(Long songId, String username) throws IOException {
        User user = userFileDao.find(username);

        if (user == null || user.getPlaylist().contains(songId))
            return false;

        user.getPlaylist().add(songId);
        userFileDao.save(user);

        return true;
    }

    public boolean removeSongFromPlaylist(Long songId, String username) throws IOException {
        User user = userFileDao.find(username);

        if (user == null || !user.getPlaylist().contains(songId))
            return false;

        user.getPlaylist().remove(songId);
        userFileDao.save(user);

        return true;
    }
}
