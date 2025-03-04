package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import com.example.consorciojaahvafx.controller.Fachada;
import lombok.Getter;

@Getter
public class Application extends javafx.application.Application {
    private final Fachada server;

    public Application() {
        this.server = Fachada.getInstance();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStg(stage);
        System.out.println(getClass().getResource("/Login.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
        //FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/screen/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("J aah Consorcio");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}