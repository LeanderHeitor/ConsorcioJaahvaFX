package com.example.consorciojaahvafx.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginCardController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text messageText;

    @FXML
    private Button signUpButton;

    @FXML
    private void initialize() {
        // Configuração inicial (opcional)
        messageText.setText("sem login ainda?");

        // Ação para o botão de cadastro
        signUpButton.setOnAction(event -> handleSignUp());
    }

    private void handleSignUp() {
        // Ação quando o usuário clica no botão de cadastro
        // Por exemplo, você pode navegar para uma tela de cadastro
        System.out.println("Cadastro iniciado.");

        // Aqui você pode adicionar lógica de navegação ou exibir um alerta
        // Exemplo de como capturar os valores dos campos:
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Aqui você pode realizar validações ou outras ações com os dados
        System.out.println("Usuário: " + username + ", Senha: " + password);
    }
}
