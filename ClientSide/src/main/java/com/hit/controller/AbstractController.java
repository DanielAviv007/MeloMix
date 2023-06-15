package com.hit.controller;

import com.hit.driver.MVCDriver;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractController {
    public void switchScene(AnchorPane currentAnchorPane, String nextFxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(MVCDriver.class.getResource(nextFxml)));

        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
