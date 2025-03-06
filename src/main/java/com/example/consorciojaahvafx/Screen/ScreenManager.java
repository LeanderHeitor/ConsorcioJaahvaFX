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
    private Scene cadastroUsuarioScene;
    private Scene abaClienteScene;

    //Controladores das telas
    private LoginController loginController;
    private LoginAdminController loginAdminController;
    private CadastroController cadastroController;
    private AbaClienteController abaClienteController;



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

    //Getters and Setters Scenes
    public Scene getLoginScene() {
        return loginScene;
    }

    public Scene getCadastroUsuarioScene() {
        return cadastroUsuarioScene;
    }
    public Scene getAbaClienteScene() {
        return abaClienteScene;
    }

    //Getters ans Setters Controllers
    public LoginController getLoginController() {
        return loginController;
    }

    public LoginAdminController getLoginAdminController() {
        return loginAdminController;
    }

    public CadastroController getCadastroController() {
        return cadastroController;
    }
    public AbaClienteController getAbaClienteController() {
        return abaClienteController;
    }

    private void carregarTelas() {
        try {
            FXMLLoader loginPane = new FXMLLoader(getClass().getResource("Login.fxml"));
            this.loginScene = new Scene(loginPane.load());
            this.loginController = loginPane.getController();

            FXMLLoader CadastroPane = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
            this.cadastroUsuarioScene = new Scene(CadastroPane.load());
            this.cadastroController = CadastroPane.getController();

            FXMLLoader abaClientePane = new FXMLLoader(getClass().getResource("AbaCliente.fxml"));
            this.abaClienteScene = new Scene(abaClientePane.load());
            this.abaClienteController = abaClientePane.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene(String fxml, String titulo) {
        switch (fxml) {
            case "Login.fxml": stg.setScene(loginScene);
            case "Cadastro.fxml": stg.setScene(cadastroUsuarioScene);
            case "AbaCliente.fxml": stg.setScene(abaClienteScene);
        }
        stg.setTitle(titulo);
    }

}
