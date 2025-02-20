package com.example.consorciojaahvafx.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConsorcioMenuController {

    @FXML
    private Button btnCadastrarGrupo;

    @FXML
    private Button btnListarGrupos;

    @FXML
    private Button btnSair;

    @FXML
    private void initialize() {
        // Configuração inicial, se necessário
    }

    @FXML
    private void cadastrarGrupo() {
        System.out.println("Cadastro de Grupo acionado");
    }

    @FXML
    private void listarGrupos() {
        System.out.println("Listagem de Grupos acionada");
    }

    @FXML
    private void sair() {
        System.out.println("Saindo do sistema");
        System.exit(0);
    }
}
