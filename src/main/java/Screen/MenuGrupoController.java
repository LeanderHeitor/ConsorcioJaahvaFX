package Screen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Admin;

public class MenuGrupoController {

    // FXML ids dos botões
    @FXML
    private Button btnCadastrarGrupo;

    @FXML
    private Button btnVisualizarGrupos;

    @FXML
    private Button btnEditarGrupo;

    @FXML
    private Button btnExcluirGrupo;

    @FXML
    private Button btnConsultarGrupo;

    @FXML
    private Button btnEmitirRelatorioGrupo;

    @FXML
    private Button btnVoltar;

    private Admin admin;

    public MenuGrupoController(Admin admin) {
        this.admin = new Admin();
    }


    @FXML
    public void handleCadastrarGrupo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cadastrar Novo Grupo");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para cadastrar novo grupo de consórcio.");
        alert.showAndWait();
    }

    @FXML
    public void handleVisualizarGrupos() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Visualizar Grupos");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para visualizar todos os grupos de consórcio.");
        alert.showAndWait();
    }

    @FXML
    public void handleEditarGrupo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Editar Grupo");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para editar os dados de um grupo de consórcio.");
        alert.showAndWait();
    }

    @FXML
    public void handleExcluirGrupo() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Excluir Grupo");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja excluir este grupo?");
        alert.showAndWait();
    }

    @FXML
    public void handleConsultarGrupo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Consultar Grupo");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para consultar os detalhes de um grupo de consórcio.");
        alert.showAndWait();
    }

    @FXML
    public void handleEmitirRelatorioGrupo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Emitir Relatório de Grupo");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para emitir relatórios financeiros de um grupo de consórcio.");
        alert.showAndWait();
    }

    @FXML
    public void handleVoltar() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Voltar");
        alert.setHeaderText(null);
        alert.setContentText("Retornar ao painel anterior.");
        alert.showAndWait();
    }

    // Método para inicializar a tela
    @FXML
    private void initialize() {
        System.out.println("Menu do Grupo de Consórcio carregado!");
    }
}
