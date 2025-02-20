package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BuscaController {


    @FXML
    private TextField txtBuscaGrupo;

    @FXML
    private Button btnBuscar;

    @FXML
    private ListView<String> listViewResultados;

    @FXML
    private Button btnCancelar;


    private ObservableList<String> grupos = FXCollections.observableArrayList(
            "Grupo A", "Grupo B", "Grupo C", "Grupo D"
    );

    @FXML
    private void initialize() {
        listViewResultados.setItems(grupos);  // Inicializa a lista com os grupos simulados
    }

    @FXML
    public void handleBuscar() {
        String pesquisa = txtBuscaGrupo.getText().toLowerCase().trim();

        if (pesquisa.isEmpty()) {
            showAlert(AlertType.ERROR, "Erro", "Digite um nome para buscar.");
            return;
        }

        ObservableList<String> resultadosFiltrados = FXCollections.observableArrayList();

        for (String grupo : grupos) {
            if (grupo.toLowerCase().contains(pesquisa)) {
                resultadosFiltrados.add(grupo);
            }
        }

        if (resultadosFiltrados.isEmpty()) {
            showAlert(AlertType.INFORMATION, "Resultado", "Nenhum grupo encontrado.");
        } else {
            listViewResultados.setItems(resultadosFiltrados); // Atualiza a lista de resultados
        }
    }

    @FXML
    public void handleCancelar() {
        txtBuscaGrupo.clear();
        listViewResultados.setItems(grupos);  // Restaura a lista completa
        System.out.println("Busca cancelada.");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
