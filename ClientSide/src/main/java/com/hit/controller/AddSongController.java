package com.hit.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddSongController extends AbstractController {
    @FXML
    private Button addSongBtn;
    @FXML
    private FontAwesomeIconView closeSongForm;
    @FXML
    private AnchorPane loginPane;


    public void addSong(MouseEvent mouseEvent) throws IOException {
        goToMain(mouseEvent);
    }

    public void goToMain(MouseEvent mouseEvent) throws IOException {
        switchScene(loginPane, "/com.hit.view/main-page.fxml");
    }
}
