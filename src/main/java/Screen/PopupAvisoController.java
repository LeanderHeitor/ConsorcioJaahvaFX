package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class PopupAvisoController {

    @FXML
    private Button btnFechar;

    public void mostrarAvisoDivida() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Aviso de Dívida");
        alert.setHeaderText("Você tem uma dívida pendente!");
        alert.setContentText("Por favor, regularize sua situação o mais rápido possível.");
        alert.showAndWait();
    }

    @FXML
    public void handleFechar() {
        // Fecha o pop-up
        btnFechar.getScene().getWindow().hide();
    }
}
