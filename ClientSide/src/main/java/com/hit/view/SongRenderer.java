package com.hit.view;

import com.hit.dm.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class SongRenderer {

    public void renderSongs(List<Song> songs, TableView<Song> songTable) {
        ObservableList<Song> songsObservableList = FXCollections.observableArrayList(songs);

        songTable.setItems(songsObservableList);
    }
}
