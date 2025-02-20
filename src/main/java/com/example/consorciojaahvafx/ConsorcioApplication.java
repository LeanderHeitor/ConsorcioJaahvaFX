package com.example.consorciojaahvafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ConsorcioApplication extends Application {

    public static void main(String[] args) {
        System.out.println("Inicializando o consorcio App");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ConsorcioApplication.class.getResource("ConsorcioMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 830);
        stage.setTitle("Consorcio JAAH");
        stage.setResizable(false);
        Image icon = new Image("src/main/resources/images/cone.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}