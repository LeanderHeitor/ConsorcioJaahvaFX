package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.consorciojaahvafx.model.Usuario;
import com.example.consorciojaahvafx.model.Admin;
import com.example.consorciojaahvafx.model.Cliente;

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
        if (rbAdmin.isSelected()) {
            if (confirmacaoSenha.isEmpty() == confirmacaoSenha.isEmpty()) {
                usuario = new Admin(nome, CPF, telefone, email, senha);
                exibirAlerta("Sucesso.", "Administrador cadastrado com sucesso.");
            } else {
                usuario = new Cliente(nome, CPF, telefone, email, senha);
                exibirAlerta("Sucesso.", "Cliente cadastrado com sucesso.");
            }
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

    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }
}
