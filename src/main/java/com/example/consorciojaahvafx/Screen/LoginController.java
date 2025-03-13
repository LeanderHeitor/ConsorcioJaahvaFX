package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.controller.Fachada;
import com.example.consorciojaahvafx.enums.TipoUsuario;
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
    private Fachada fachada;

    @FXML
    private TextField tfLoginCpf, tfTipoUsuario;

    @FXML
    private PasswordField tfSenhaLogin;

    @FXML
    private Button loginButton;

    @FXML
    private Button cadastrarButton;


    @FXML
    private Button btSair;

    @FXML
    private Text mensagemText;

    @FXML
    public void initialize() {
        mensagemText.setVisible(false);
        this.fachada = new Fachada();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException, LoginException {
        String cpf = tfLoginCpf.getText();
        String password = tfSenhaLogin.getText();

        if (cpf.isEmpty() || password.isEmpty()) {
            mensagemText.setVisible(true);
            mensagemText.setText("Preencha os campos");
            return;
        }

        TipoUsuario tipo = TipoUsuario.valueOf(tfTipoUsuario.getText());
        fachada.checarLogin(cpf, password);


        System.out.println("Tipo do usuário: " + tipo);


        if (tipo == null) {
            mensagemText.setVisible(true);
            mensagemText.setText("Erro interno no login.");
            return;
        }

        switch (tipo) {
            case NAO_ENCONTRADO:
                mensagemText.setVisible(true);
                mensagemText.setText("Usuário não encontrado");
                return;

            case SENHA_INCORRETA:
                mensagemText.setVisible(true);
                mensagemText.setText("Senha incorreta");
                return;

            case CLIENTE:
                abrirTela("/abaCliente.fxml", "Cliente");
                break;

            case ADMIN:
                abrirTela("/abaAdmin.fxml", "Administrador");
                break;
        }

        Stage telaLogin = (Stage) loginButton.getScene().getWindow();
        telaLogin.close();
    }

    private void abrirTela(String caminhoFXML, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
        }
    


    @FXML
    private void sairButtonAction() {
        System.exit(0);
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
