package com.hit.controller;

import com.hit.dm.Song;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainPageController extends AbstractController {
    @FXML
    private FontAwesomeIconView logOutBtn;
    @FXML
    private AnchorPane loginPane;
    @FXML
    public AnchorPane buttonsPane;
    @FXML
    private TableView<Song> songTable;
    @FXML
    private TableColumn<Song, String> title;
    @FXML
    private TableColumn<Song, String> artist;
    @FXML
    private TableColumn<Song, String> lyrics;
    @FXML
    private TableColumn<Song, Boolean> playlist;
    @FXML
    private Button addNewSongBtn;
    @FXML
    private Button showMyPlaylist;

    @FXML
    void goToAddSongPage(MouseEvent event) throws IOException {
        switchScene(loginPane, "/com.hit.view/add-song-page.fxml");
    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        switchScene(loginPane, "/com.hit.view/login-page.fxml");
    }

}
