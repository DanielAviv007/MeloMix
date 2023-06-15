module com.hit.clientside {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;


    opens com.hit.view to javafx.fxml, javafx.graphics;
    exports com.hit.driver;
    opens com.hit.controller to javafx.fxml, javafx.graphics;
    exports com.hit.controller;
}