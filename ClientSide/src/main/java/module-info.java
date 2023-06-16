module com.hit.clientside {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.google.gson;


    opens com.hit.view to javafx.fxml, javafx.graphics;
    exports com.hit.driver;
    opens com.hit.controller to javafx.fxml, javafx.graphics;
    exports com.hit.controller;
    opens com.hit.client to javafx.fxml, javafx.graphics;
    exports com.hit.client;
}