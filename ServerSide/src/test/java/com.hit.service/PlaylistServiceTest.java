package com.hit.service;

import com.hit.config.Config;
import com.hit.dm.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;

public class PlaylistServiceTest {
    private static final String TEST_CONFIG_PATH = "src/test/java/resources/test_config.json";

    @Test
    public void addSongToPlaylist_UserDoesNotExists_FAILURE() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);
        PlaylistService playlistService = new PlaylistService();

        Assertions.assertFalse(playlistService.addSongToPlaylist(1L, "dummy_not_exists"));

        File file = new File(config.getUsersPath());
        file.delete();
    }

    @Test
    public void addSongToPlaylist_UserAlreadyHasTheSong_FAILURE() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        PlaylistService playlistService = new PlaylistService();

        User user = new User("dummy");
        user.addSongToPlaylist(1L);

        playlistService.userFileDao.save(user);

        Assertions.assertFalse(playlistService.addSongToPlaylist(1L, "dummy"));

        File file = new File(config.getUsersPath());
        file.delete();
    }

    @Test
    public void addSongToPlaylist_ValidInsertion_SUCCESS() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        PlaylistService playlistService = new PlaylistService();

        User user = new User("dummy");

        playlistService.userFileDao.save(user);

        Assertions.assertTrue(playlistService.addSongToPlaylist(1L, "dummy"));

        File file = new File(config.getUsersPath());
        file.delete();
    }

    @Test
    public void removeSongFromPlaylist_ValidDeletion_SUCCESS() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        PlaylistService playlistService = new PlaylistService();

        User user = new User("dummy");
        user.addSongToPlaylist(1L);

        playlistService.userFileDao.save(user);

        Assertions.assertTrue(playlistService.removeSongFromPlaylist(1L, "dummy"));

        File file = new File(config.getUsersPath());
        file.delete();
    }
}
