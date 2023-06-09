package com.hit.controller;

import com.hit.dm.Song;
import com.hit.service.PlaylistService;

import java.io.IOException;
import java.util.List;

public class PlaylistController {
    private final PlaylistService playlistService;
    private final SongController songController;

    public PlaylistController() throws IOException {
        playlistService = new PlaylistService();
        songController = new SongController();
    }

    public List<Song> getUserPlaylist(List<Long> playlistIds) throws IOException {
        return songController.getSongsByIds(playlistIds);
    }

    public boolean addSongToPlaylist(Long songId, String username) throws IOException {
        return playlistService.addSongToPlaylist(songId, username);
    }

    public boolean removeSongFromPlaylist(Long songId, String username) throws IOException {
        return playlistService.removeSongFromPlaylist(songId, username);
    }
}
