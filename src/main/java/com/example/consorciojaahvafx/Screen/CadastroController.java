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
import com.example.consorciojaahvafx.controller.Fachada;

import java.io.IOException;

public class CadastroController {
    private Fachada fachada;

    @FXML
    private TextField tfNome, tfCPF,tfTelefone, tfEmail;

    @FXML
    private PasswordField pfSenha, pfConfirmacaoSenha;

    @FXML
    private Button btFinCadastro;

    @FXML
    private Button sairButton;

    @FXML
    private RadioButton btCadastrarAdmin;

    @FXML
    public void initialize() {
        this.fachada = new Fachada();
    }

    @FXML
    private void cadastrarUsuario() throws IOException {
        String nome = this.tfNome.getText();
        String cpf = this.tfCPF.getText().trim(); // CPF agora é String
        String telefone = this.tfTelefone.getText();
        String email = this.tfEmail.getText();
        String senha = this.pfSenha.getText();
        String confirmacaoSenha = this.pfConfirmacaoSenha.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Erro", "Preencha todos os campos!");
            return;
        }

        if (!cpf.matches("\\d+")) { // Verifica se o CPF contém apenas números
            exibirAlerta("Erro", "CPF inválido! Digite apenas números.");
            return;
        }

        Usuario usuario;
        if(senha.equals(confirmacaoSenha)) {
            if(btCadastrarAdmin.isSelected()) {
                usuario = new Admin(nome, cpf, telefone, email, senha);
                fachada.cadastrarUsuario((Admin)usuario);
                exibirAlerta("Sucesso", "Administrador cadastrado com sucesso.");
                limparCampos();
                carregarTelaLogin();

            } else {
                usuario = new Cliente(nome, cpf, telefone, email, senha);
                fachada.cadastrarCliente((Cliente) usuario);
                exibirAlerta("Sucesso", "Cliente cadastrado com sucesso.");
                limparCampos();
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
        tfNome.clear();
        tfCPF.clear();
        tfTelefone.clear();
        tfEmail.clear();
        pfSenha.clear();
        pfConfirmacaoSenha.clear();
        btCadastrarAdmin.setSelected(false);
    }

    private void carregarTelaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) btFinCadastro.getScene().getWindow();
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
