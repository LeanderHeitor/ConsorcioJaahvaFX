package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErroCardController {

    @FXML
    private Label lblErrorMessage;

    @FXML
    private Button btnClose;

    /**
     * Define a mensagem de erro a ser exibida no label.
     * @param message Texto do erro
     */
    public void setErrorMessage(String message) {
        lblErrorMessage.setText(message);
    }

    @FXML
    private void closeErrorDialog() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
