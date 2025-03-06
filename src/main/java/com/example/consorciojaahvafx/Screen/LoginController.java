package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.controller.Fachada;
import com.example.consorciojaahvafx.exception.UsuarioNaoExisteException;
import com.example.consorciojaahvafx.model.Admin;
import javafx.event.ActionEvent;
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

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    private Application app;

    @FXML
    private TextField CPF;

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

    @FXML
    public void initialize() {
        mensagemText.setVisible(false);
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException, LoginException {
        String cpf = String.valueOf(Long.valueOf(CPF.getText()));
        String password = passwordField.getText();

        if (cpf.isEmpty() || password.isEmpty()) {
            mensagemText.setVisible(true);
            mensagemText.setText("Preencha os campos");
        } else {
            try {
                app.getServer().checarLogin(Long.valueOf(cpf), password);
            } catch (UsuarioNaoExisteException e) {
                mensagemText.setVisible(true);
                mensagemText.setText("Usuário não encontrado");

                ScreenManager sm = new ScreenManager().getInstance();

               // if (e.getFxml().equals("abaCliente.fxml")) { IMPLEMENTAR TODA ESSA LÓGICA.
                 //   sm.getAbaClienteController().setClienteLogado(e.getUsuario);
                }
            }
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
