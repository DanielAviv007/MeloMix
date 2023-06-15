package com.hit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginPageController extends com.hit.controller.AbstractController {
    @FXML
    private Button loginBtn;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField usernameField;

    @FXML
    void onLoginBtnClick(ActionEvent event) throws IOException {
        switchScene(loginPane, "/com.hit.view/main-page.fxml");
    }
}

