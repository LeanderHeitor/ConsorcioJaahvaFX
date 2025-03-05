package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class LoginAdminController {

    @FXML
    private TextField adminName;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button sairButton;

    @FXML
    private TextField mensagemText;

    public void initialize() {}

    @FXML
    private void handleLoginButtonAction() {
        String username = adminName.getText();
        String password = adminPassword.getText();

        if (username.equals("cliente") && password.equals("123456")) {
            mensagemText.setText("Login efetuado com sucesso!");
        } else {
            mensagemText.setText("Usuário ou senha incorretos!");
        }
    }

    @FXML
    private void handleSairButtonAction() {
        System.exit(0);
    }

    @FXML
    private void handleVoltarButtonAction() {}

}
