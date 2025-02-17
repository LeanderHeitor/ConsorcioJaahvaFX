package com.consorcio.app.ConsorcioApp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ConsorcioApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Consorcio JAAH");
        stage.setResizable(false);
        Image icon = new Image("src/main/resources/images/cone.png");
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 830);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}