package com.example.consorciojaahvafx.Screen;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    private Application app;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cadastrarButton;

    @FXML
    private Hyperlink adminLink;

    @FXML
    private Button sairButton;

    @FXML
    private Text mensagemText;

    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("cliente") && password.equals("123456")) {
            mensagemText.setText("Login efetuado com sucesso!");
        } else {
            mensagemText.setText("Usuário ou senha incorretos!");
        }
    }


    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }

    @FXML
    private void handleAdminLinkAction() {
        mensagemText.setText("Redirecionando para o login de administrador...");
    }

    @FXML
    private void abrirTelaCadastro() {
        try{
            System.out.println(getClass().getResource("/cadastro.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cadastro.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Cadastro");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) cadastrarButton.getScene().getWindow();
            telaLogin.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
