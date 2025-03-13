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
    private Scene abaAdminScene; //cena para de login para a tela do Admin
    private Scene abaGrupoClienteScene; //cena da tela de cliente para a tela dos grupos dos clientes
    private Scene abaClienteConsorciosScene; //Cena da tela de grupos do cliente para a tela do consorcio do cliente
    private Scene abaClienteDarLanceScene; //Cena da tela de consórcio do cliente para a tela para dar lance
    private Scene abaClienteSimulacaoScene;  //cena da tela de consórcio do cliente para a tela de fazer uma simulação
    private Scene abaAdminConsorcioScene; // cena da tela do admin para os consórcios que o admin administra
    private Scene abaAdminGrupoClienteInfoScene; //cena da tela de admin para as informações dos grupos que aquele admin administra
    private Scene abaAdminGrupoInfoDetalhesScene; // cena da tela de grupos do admin para tela de informações detalhadas do grupo
    private Scene abaAdminClienteInfoScene; //cena da tela de informações de infos detalhadas grupo para tela de informações do cliente

    //Controladores das telas
    private LoginController loginController;
    private CadastroController cadastroController;
    private AbaClienteController abaClienteController;
    private AbaAdminController abaAdminController;
    private AbaGrupoClienteController abaGrupoClienteController;
    private AbaConsorciosClienteController abaConsorciosClienteController;
    private AbaClienteDarLanceController abaClienteDarLanceController;
    private AbaClienteSimulacaoController abaClienteSimulacaoController;
    private AbaAdminConsorcioInfo abaAdminConsorcioInfo;
    private AbaAdminGrupoInfoController abaAdminGrupoInfoController;
    private AbaAdminGrupoInfoDetalhesController abaAdminGrupoInfoDetalhesController;
    private AbaAdminClienteInfoController abaAdminClienteInfoController;



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
    public Scene getAbaAdmin(){return abaAdminScene;}
    public Scene getAbaGrupoClienteScene() {return abaGrupoClienteScene;}
    public Scene getAbaClienteConsorciosScene() {return abaClienteConsorciosScene;}
    public Scene getAbaClienteDarLanceScene() {return abaClienteDarLanceScene;}
    public Scene getAbaClienteSimulacaoScene() {return abaClienteSimulacaoScene;}
    public Scene getAbaAdminConsorcioScene() {return abaAdminConsorcioScene;}
    public Scene getAbaAdminGrupoClienteInfoScene() {return abaAdminGrupoClienteInfoScene;}
    public Scene getAbaAdminGrupoInfoDetalheScene() {return abaAdminGrupoInfoDetalhesScene;}
    public Scene getAbaAdminClienteInfoScene() {return abaAdminClienteInfoScene;}

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
    public AbaAdminController getAbaAdminController() {return abaAdminController;}
    public AbaGrupoClienteController getAbaGrupoClienteController() {return abaGrupoClienteController;}
    public AbaConsorciosClienteController getAbaConsorciosClienteController() {return abaConsorciosClienteController;}
    public AbaClienteDarLanceController getAbaClienteDarLanceController() {return abaClienteDarLanceController;}
    public AbaClienteSimulacaoController getAbaClienteSimulacaoController() {return abaClienteSimulacaoController;}
    public AbaAdminConsorcioInfo getAbaAdminConsorcioInfo() {return abaAdminConsorcioInfo;}
    public AbaAdminGrupoInfoController getAbaAdminGrupoInfoController() {return abaAdminGrupoInfoController;}
    public AbaAdminGrupoInfoDetalhesController getAbaAdminGrupoInfoDetalhesController() {return abaAdminGrupoInfoDetalhesController;}
    public AbaAdminClienteInfoController getAbaAdminClienteInfoController() {return abaAdminClienteInfoController;}

    private void carregarTelas() {
        try {
            FXMLLoader loginPane = new FXMLLoader(getClass().getResource("/Login.fxml"));
            this.loginScene = new Scene(loginPane.load());
            this.loginController = loginPane.getController();

            FXMLLoader CadastroPane = new FXMLLoader(getClass().getResource("/Cadastro.fxml"));
            this.cadastroUsuarioScene = new Scene(CadastroPane.load());
            this.cadastroController = CadastroPane.getController();

            FXMLLoader abaClientePane = new FXMLLoader(getClass().getResource("/AbaCliente.fxml"));
            this.abaClienteScene = new Scene(abaClientePane.load());
            this.abaClienteController = abaClientePane.getController();

            FXMLLoader abaAdminPane = new FXMLLoader(getClass().getResource("/AbaAdmin.fxml"));
            this.abaAdminScene = new Scene(abaAdminPane.load());
            this.abaAdminController = abaAdminPane.getController();

            FXMLLoader abaGrupoClientePane = new FXMLLoader(getClass().getResource("/AbaGrupoCliente.fxml"));
            this.abaGrupoClienteScene = new Scene(abaGrupoClientePane.load());
            this.abaGrupoClienteController = abaClientePane.getController();

            FXMLLoader abaClienteConsorciosPane = new FXMLLoader(getClass().getResource("/AbaClienteConsorcios.fxml"));
            this.abaClienteConsorciosScene = new Scene(abaClienteConsorciosPane.load());
            this.abaConsorciosClienteController = abaClientePane.getController();

            FXMLLoader abaClienteDarLancePane = new FXMLLoader(getClass().getResource("/AbaClienteDarLance.fxml"));
            this.abaClienteDarLanceScene = new Scene(abaClienteDarLancePane.load());
            this.abaClienteDarLanceController = abaClientePane.getController();

            FXMLLoader abaClienteSimulacaoPane = new FXMLLoader(getClass().getResource("/AbaClienteSimulacao.fxml"));
            this.abaClienteSimulacaoScene = new Scene(abaClienteSimulacaoPane.load());
            this.abaClienteSimulacaoController = abaClientePane.getController();

            FXMLLoader abaAdminConsorcioInfoPane =  new FXMLLoader(getClass().getResource("/AbaAdminConsorcioInfo.fxml"));
            this.abaAdminConsorcioScene = new Scene(abaAdminConsorcioInfoPane.load());
            this.abaAdminConsorcioInfo = abaAdminPane.getController();

            FXMLLoader abaAdminClienteInfoPane = new FXMLLoader(getClass().getResource("/AbaAdminClienteInfo.fxml"));
            this.abaAdminClienteInfoScene = new Scene(abaAdminClienteInfoPane.load());
            this.abaAdminClienteInfoController = abaAdminPane.getController();

            FXMLLoader abaAdminGrupoInfoPane = new FXMLLoader(getClass().getResource("/AbaAdminGrupoInfo.fxml"));
            this.abaAdminGrupoClienteInfoScene = new Scene(abaAdminGrupoInfoPane.load());
            this.abaAdminGrupoInfoController = abaAdminPane.getController();

            FXMLLoader abaAdminGrupoInfoDetalhesPane = new FXMLLoader(getClass().getResource("/AbaAdminGrupoInfoDetalhe.fxml"));
            this.abaAdminGrupoInfoDetalhesScene = new Scene(abaAdminGrupoInfoDetalhesPane.load());
            this.abaAdminGrupoInfoDetalhesController = abaAdminPane.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene(String fxml, String titulo) {
        switch (fxml) {
            case "Login.fxml": stg.setScene(loginScene);
            case "Cadastro.fxml": stg.setScene(cadastroUsuarioScene);
            case "AbaCliente.fxml": stg.setScene(abaClienteScene);
            case "AbaAdmin.fxml": stg.setScene(abaAdminScene);
            case "AbaGrupoCliente.fxml": stg.setScene(abaGrupoClienteScene);
            case "AbaConsorciosCliente.fxml": stg.setScene(abaClienteConsorciosScene);
            case "AbaClienteDarLance.fxml": stg.setScene(abaClienteDarLanceScene);
            case "AbaClienteSimulacao.fxml": stg.setScene(abaClienteSimulacaoScene);
            case "AbaAdminConsorciosInfo.fxml": stg.setScene(abaAdminConsorcioScene);
            case "AbaAdminClienteInfo.fxml": stg.setScene(abaAdminClienteInfoScene);
            case "AbaAdminGrupoInfo.fxml": stg.setScene(abaAdminGrupoClienteInfoScene);
            case "AbaAdminGrupoIntoDetalhes.fxml":stg.setScene(abaAdminGrupoInfoDetalhesScene);
        }
        stg.setTitle(titulo);
    }

}
