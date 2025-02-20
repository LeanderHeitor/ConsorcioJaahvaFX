package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import model.Admin;

public class PainelAdminController {

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
    private Button btnConsultarClientes;

    @FXML
    private Button btnHistContemplacoes;

    @FXML
    private Button btnConsultarContratos;

    @FXML
    private Button btnEmitirRelatorios;

    @FXML
    private Button btnSair;

    private Admin admin;

    // Construtor ou método para inicializar o admin
    public PainelAdminController() {
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
    public void handleConsultarClientes() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Consultar Clientes");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para consultar os clientes cadastrados.");
        alert.showAndWait();
    }

    @FXML
    public void handleHistContemplacoes() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Histórico de Contemplações");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para visualizar o histórico de contemplações.");
        alert.showAndWait();
    }

    @FXML
    public void handleConsultarContratos() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Consultar Contratos");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para consultar os contratos de consórcio.");
        alert.showAndWait();
    }

    @FXML
    public void handleEmitirRelatorios() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Emitir Relatórios");
        alert.setHeaderText(null);
        alert.setContentText("Abrir tela para emitir relatórios financeiros dos grupos.");
        alert.showAndWait();
    }

    @FXML
    public void handleSair() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja sair?");
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        System.out.println("Painel de Administração carregado!");
    }
}
