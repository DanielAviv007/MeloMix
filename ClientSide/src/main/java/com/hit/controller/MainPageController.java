package com.hit.controller;

import com.hit.dm.Song;
import com.hit.dm.User;
import com.hit.model.*;
import com.hit.view.SongRenderer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.*;

public class MainPageController extends AbstractController {
    public static User currentConnectedUser;

    @FXML private Button addToPlaylistBtn;
    @FXML private FontAwesomeIconView logOutBtn;
    @FXML private AnchorPane loginPane;
    @FXML public AnchorPane buttonsPane;
    @FXML private TableView<Song> songTable;
    @FXML private TableColumn<Song, String> title;
    @FXML private TableColumn<Song, String> artist;
    @FXML private TableColumn<Song, String> lyrics;
    @FXML private TableColumn<Song, Button> playlist;
    @FXML private Button addNewSongBtn;
    @FXML private Button showMyPlaylist;
    @FXML private Button modifyPlaylistSong;
    @FXML private FontAwesomeIconView refresh;
    @FXML private ChoiceBox<String> filterMenu;
    @FXML private FontAwesomeIconView searchBtn;
    @FXML private TextField searchBarContent;


    @FXML void goToAddSongPage(MouseEvent event) throws IOException {
        switchScene(loginPane, "/com.hit.view/add-song-page.fxml");
    }

    @FXML void logOut(MouseEvent event) throws IOException {
        switchScene(loginPane, "/com.hit.view/login-page.fxml");
    }

    @FXML void initialize() {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        artist.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        lyrics.setCellValueFactory(new PropertyValueFactory<>("lyrics"));

        filterMenu.getItems().setAll("Title", "Artist Name", "Lyrics");

        // Add a listener to detect selection changes in the table
        songTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                updateButtonLabel(newValue);
            }
        });
    }

    private void updateButtonLabel(Song selectedSong) {
        if (selectedSong != null) {
            Long songId = selectedSong.getId();

            if (!currentConnectedUser.getPlaylist().contains(songId))
                modifyPlaylistSong.setText("Add to Playlist");
            else
                modifyPlaylistSong.setText("Remove from Playlist");
        } else
            modifyPlaylistSong.setText("[No Selected Song]");
    }

    @FXML void onModifySongClicked(MouseEvent event) {
        int idx = songTable.getSelectionModel().getSelectedIndex();

        if (idx == -1 || currentConnectedUser == null)
            return;

        Long songId = songTable.getItems().get(idx).id;
//        this is the button
        //        @FXML private Button modifyPlaylistSong;

//        not in playlist
        if (!currentConnectedUser.playlist.contains(songId)) {
            new AddToPlaylist().addToPlaylist(currentConnectedUser.username, songId);
            modifyPlaylistSong.setText("Add to playlist");
        }
//        in playlist
        else {
            new RemoveFromPlaylist().removeFromPlaylist(currentConnectedUser.username, songId);
            modifyPlaylistSong.setText("Remove from playlist");
        }
    }

    @FXML void onRefresh(MouseEvent event) {
        SongRenderer songRenderer = new SongRenderer();

        songRenderer.renderSongs(new GetAllSongs().getSongs(), songTable);
    }

    @FXML void onSearchClicked(MouseEvent event) {
        Map<String, String> filterToRequest = new HashMap<>();

        filterToRequest.put("Title", "title");
        filterToRequest.put("Artist Name", "artist");
        filterToRequest.put("Lyrics", "lyrics");

        if (searchBarContent.getText().isEmpty() || filterMenu.getValue().isEmpty())
            return;

        SongRenderer songRenderer = new SongRenderer();

        songRenderer.renderSongs(new GetFilteredSongs().getFilteredSongs(filterToRequest.get(filterMenu.getValue()), searchBarContent.getText()), songTable);
    }


    @FXML void showPlaylist(MouseEvent event) {
        if (currentConnectedUser == null)
            return;

        SongRenderer songRenderer = new SongRenderer();

        songRenderer.renderSongs(new GetAllPlaylist().getPlaylist(currentConnectedUser.username), songTable);
    }


}