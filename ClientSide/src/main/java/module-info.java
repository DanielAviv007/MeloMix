module com.hit.clientside {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.google.gson;


    opens com.hit.view to javafx.fxml, javafx.graphics, javafx.base;
    exports com.hit.driver;

    opens com.hit.controller to javafx.fxml, javafx.graphics, javafx.base;
    exports com.hit.controller;

    opens com.hit.client to javafx.fxml, javafx.graphics, javafx.base;
    exports com.hit.client;

    opens com.hit.dm to javafx.base;
    exports com.hit.dm;
}