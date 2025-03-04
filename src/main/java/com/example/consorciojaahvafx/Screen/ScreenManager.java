package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;

    //Declarações dos métodos
    private Scene loginScene;

    //Controladores das telas
    private LoginController loginController;
    private LoginAdminController loginAdminController;



    public ScreenManager() {
        this.carregarTelas();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public static Stage getStage() {
        return stg;
    }

    public static void setStg(Stage stg) {
        ScreenManager.stg = stg;
    }

    //Getters and Setters
    public Scene getLoginScene() {
        return loginScene;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public LoginAdminController getLoginAdminController() {
        return loginAdminController;
    }

    private void carregarTelas() {
        try {
            FXMLLoader loginPane = new FXMLLoader(getClass().getResource("Login.fxml"));
            this.loginScene = new Scene(loginPane.load());
            this.loginController = loginPane.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene(String fxml, String titulo) {
        switch (fxml) {
            case "Login.fxml": stg.setScene(loginScene);
        }
        stg.setTitle(titulo);
    }

}
