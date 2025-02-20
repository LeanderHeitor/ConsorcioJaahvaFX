package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory;

public class CriarGrupoAdminController {

    // FXML ids dos campos de entrada e botões
    @FXML
    private TextField txtNomeGrupo;

    @FXML
    private TextField txtValorTotal;

    @FXML
    private Spinner<Integer> spinnerParticipantes;

    @FXML
    private TextField txtTaxaAdm;

    @FXML
    private Button btnCriarGrupo;

    @FXML
    private Button btnCancelar;

    // Método de inicialização (configuração do Spinner)
    @FXML
    private void initialize() {
        // Configurar o Spinner para o número de participantes
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spinnerParticipantes.setValueFactory(valueFactory);
    }

    // Método para criar o grupo
    @FXML
    public void handleCriarGrupo() {
        String nomeGrupo = txtNomeGrupo.getText();
        String valorTotal = txtValorTotal.getText();
        int numeroParticipantes = spinnerParticipantes.getValue();
        String taxaAdm = txtTaxaAdm.getText();

        // Validação de campos
        if (nomeGrupo.isEmpty() || valorTotal.isEmpty() || taxaAdm.isEmpty()) {
            showAlert(AlertType.ERROR, "Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            double valorTotalDouble = Double.parseDouble(valorTotal);
            double taxaAdmDouble = Double.parseDouble(taxaAdm);

            // Simular a criação do grupo (adicionar à lista ou banco de dados)
            showAlert(AlertType.INFORMATION, "Sucesso", "Grupo criado com sucesso!");

            // Limpar os campos após criar o grupo
            txtNomeGrupo.clear();
            txtValorTotal.clear();
            spinnerParticipantes.getValueFactory().setValue(1);
            txtTaxaAdm.clear();
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erro", "O valor total e a taxa de administração devem ser números válidos.");
        }
    }

    // Método de cancelar a criação do grupo
    @FXML
    public void handleCancelar() {
        // Limpar os campos e voltar à tela anterior
        txtNomeGrupo.clear();
        txtValorTotal.clear();
        spinnerParticipantes.getValueFactory().setValue(1);
        txtTaxaAdm.clear();

        System.out.println("Operação cancelada.");
    }

    // Método para exibir alertas
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
