package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroUsuarioController {

    // FXML ids dos campos de entrada e botões
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    // Método de cadastro
    @FXML
    public void handleCadastrar() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            showAlert(AlertType.ERROR, "Erro", "Todos os campos são obrigatórios!");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            showAlert(AlertType.ERROR, "Erro", "As senhas não coincidem!");
            return;
        }

        showAlert(AlertType.INFORMATION, "Sucesso", "Usuário cadastrado com sucesso!");

        txtNome.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtConfirmarSenha.clear();
    }

    @FXML
    public void handleCancelar() {
        txtNome.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtConfirmarSenha.clear();

        System.out.println("Cadastro cancelado.");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        System.out.println("Formulário de Cadastro de Usuário carregado!");
    }
}
