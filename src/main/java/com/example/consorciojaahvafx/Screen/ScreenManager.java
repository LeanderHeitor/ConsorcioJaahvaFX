package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;

    //Declarações dos métodos
    private Scene loginScene; //cena do login
    private Scene cadastroUsuarioScene; //cena para a tela de cadastro
    private Scene abaClienteScene; //cena da cena de login para a tela do cliente
    private Scene abaGrupoClienteScene; //cena da tela de cliente para a tela dos grupos dos clientes
    private Scene abaClienteConsorcios; //Cena da tela de grupos do cliente para a tela do consorcio do cliente

    //Controladores das telas
    private LoginController loginController;
    private CadastroController cadastroController;
    private AbaClienteController abaClienteController;
    private AbaGrupoClienteController abaGrupoClienteController;
    private AbaConsorciosClienteController abaConsorciosClienteController;



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
    public Scene getAbaGrupoClienteScene() {return abaGrupoClienteScene;}
    public Scene getAbaClienteConsorcios() {return abaClienteConsorcios;}

    //Getters ans Setters Controllers
    public LoginController getLoginController() {
        return loginController;
    }
    public CadastroController getCadastroController() {
        return cadastroController;
    }
    public AbaClienteController getAbaClienteController() {
        return abaClienteController;
    }
    public AbaGrupoClienteController getAbaGrupoClienteController() {return abaGrupoClienteController;}
    public AbaConsorciosClienteController getAbaConsorciosClienteController() {return abaConsorciosClienteController;}

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

            FXMLLoader abaGrupoClientePane = new FXMLLoader(getClass().getResource("AbaGrupoCliente.fxml"));
            this.abaGrupoClienteScene = new Scene(abaClientePane.load());
            this.abaGrupoClienteController = abaClientePane.getController();

            FXMLLoader abaClienteConsorciosPane = new FXMLLoader(getClass().getResource("AbaClienteConsorcios.fxml"));
            this.abaClienteConsorcios = new Scene(abaClientePane.load());
            this.abaConsorciosClienteController = abaClientePane.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene(String fxml, String titulo) {
        switch (fxml) {
            case "Login.fxml": stg.setScene(loginScene);
            case "Cadastro.fxml": stg.setScene(cadastroUsuarioScene);
            case "AbaCliente.fxml": stg.setScene(abaClienteScene);
            case "AbaGrupoCliente.fxml": stg.setScene(abaGrupoClienteScene);
            case "AbaConsorciosCliente.fxml": stg.setScene(abaClienteConsorcios);
        }
        stg.setTitle(titulo);
    }

}
