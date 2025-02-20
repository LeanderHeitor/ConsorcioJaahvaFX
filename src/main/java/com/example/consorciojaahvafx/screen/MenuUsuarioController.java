package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class MenuUsuarioController {

    @FXML
    private Button btnVisualizarPerfil;

    @FXML
    private Button btnAlterarSenha;

    @FXML
    private Button btnSair;

    @FXML
    public void handleVisualizarPerfil() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Visualizar Perfil");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para visualizar as informações do perfil.");
        alert.showAndWait();
    }

    // Método para alterar a senha
    @FXML
    public void handleAlterarSenha() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alterar Senha");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para alterar a senha do usuário.");
        alert.showAndWait();
    }

    // Método para sair do sistema
    @FXML
    public void handleSair() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza de que deseja sair?");
        alert.showAndWait();

    }

    // Método para inicializar a tela
    @FXML
    private void initialize() {
        System.out.println("Menu do Usuário carregado!");
    }
}
