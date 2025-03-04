module com.example.consorcioJaahvaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires kernel;
    requires layout;
    requires io;


    opens Screen to javafx.fxml, javafx.base;
    exports Screen;

    opens model to javafx.fxml;
    exports model;

    opens controller to javafx.fxml;
    exports controller;
}