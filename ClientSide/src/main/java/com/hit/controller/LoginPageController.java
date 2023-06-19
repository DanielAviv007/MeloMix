package com.hit.controller;

import com.hit.dm.User;
import com.hit.model.LoginUser;
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
//        transferring user to the next controller
        MainPageController.currentConnectedUser = new LoginUser().loginUser(usernameField.getText());;

        switchScene(loginPane, "/com.hit.view/main-page.fxml");
    }
}