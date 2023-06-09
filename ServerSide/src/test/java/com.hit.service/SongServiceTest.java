package com.hit.service;

import com.hit.config.Config;
import com.hit.dm.Song;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;

public class SongServiceTest {
    private static final String TEST_CONFIG_PATH = "src/test/java/resources/test_config.json";

    @Test
    public void addNewSong_NotExistingSong_SUCCESS() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        SongService songService = new SongService();
        Long id = 0L;
        boolean found = false;

        Song song = new Song("dummy", "", "");
        id = song.getId();

        songService.addNewSong(song);

        for (Song s : songService.getAllSongs())
            if (s.getId().equals(id)) {
                found = true;
                break;
            }

        File file = new File(config.getSongsPath());
        file.delete();
    }

    @Test
    public void searchSongsByTitle_addingAndSearching_SUCCESS() throws IOException {
        Config config = Config.getInstance();

        config.loadConfig(TEST_CONFIG_PATH);

        SongService songService = new SongService();

        songService.addNewSong(new Song("dummy", "", ""));
        songService.addNewSong(new Song("ummy", "", ""));
        songService.addNewSong(new Song("umm", "", ""));
        songService.addNewSong(new Song("xyz", "", ""));

        Assertions.assertEquals(3, songService.searchSongsByTitle("mm").size());

        File file = new File(config.getSongsPath());
        file.delete();
    }
}
