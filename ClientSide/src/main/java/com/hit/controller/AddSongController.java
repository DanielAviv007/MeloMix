package com.hit.controller;

import com.hit.model.AddNewSong;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class AddSongController extends AbstractController {
    @FXML
    private Button addSongBtn;
    @FXML
    private FontAwesomeIconView closeSongForm;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField artist;
    @FXML
    private TextArea lyrics;
    @FXML
    private TextField title;

    public void addSong(MouseEvent mouseEvent) throws IOException {
        new AddNewSong().addNewSong(title.getText(), artist.getText(), lyrics.getText());
        goToMain(mouseEvent);
    }

    public void goToMain(MouseEvent mouseEvent) throws IOException {
        switchScene(loginPane, "/com.hit.view/main-page.fxml");
    }
}
