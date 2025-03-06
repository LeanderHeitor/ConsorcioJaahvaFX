package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.consorciojaahvafx.model.Usuario;
import com.example.consorciojaahvafx.model.Admin;
import com.example.consorciojaahvafx.model.Cliente;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML
    private TextField nome, CPF, Telefone, Email;

    @FXML
    private PasswordField senha, confirmacaoSenha;

    @FXML
    private Button cadastrar;

    @FXML
    private Button sairButton;

    @FXML
    private RadioButton rbAdmin;

    @FXML
    private void cadastrarUsuario() {
        String nome = this.nome.getText();
        long CPF;
        try {CPF = Long.parseLong(this.CPF.getText());
        } catch (NumberFormatException e) {
            System.out.println("Erro, insira apenas números. ");
            return;
        }
        String telefone = this.Telefone.getText();
        String email = this.Email.getText();
        String senha = this.senha.getText();
        String confirmacaoSenha = this.confirmacaoSenha.getText();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Erro", "Preencha todos os campos!");
            return;
        }

        Usuario usuario;
        if(senha.equals(confirmacaoSenha)) {
            if(rbAdmin.isSelected()) {
                usuario = new Admin(nome, CPF, telefone, email, senha);
                exibirAlerta("Sucesso.", "Administrador cadastrado com sucesso.");
                carregarTelaLogin();
            } else {
                usuario = new Cliente(nome, CPF, telefone, email, senha);
                exibirAlerta("Sucesso.", "Cliente cadastrado com sucesso.");
                carregarTelaLogin();
            }
        } else {
            exibirAlerta("Erro", "Senhas não são iguais. Tente novamente.");
        }
    }

    private void exibirAlerta (String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        nome.clear();
        CPF.clear();
        Telefone.clear();
        Email.clear();
        senha.clear();
        confirmacaoSenha.clear();
        rbAdmin.setSelected(false);
    }

    private void carregarTelaLogin() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) cadastrar.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }
}
