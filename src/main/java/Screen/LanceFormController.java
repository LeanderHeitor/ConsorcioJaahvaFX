package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LanceFormController {

    @FXML
    private Text txtVehicleName;

    @FXML
    private TextField txtBidValue;

    @FXML
    private TextField txtConsortiumCode;

    @FXML
    private Button btnConfirm;

    /**
     * Método para inicializar os componentes (executado automaticamente pelo JavaFX).
     */
    @FXML
    public void initialize() {
        btnConfirm.setOnAction(event -> confirmarLance());
    }

    /**
     * Define o nome do veículo no formulário.
     * @param name Nome do veículo
     */
    public void setVehicleName(String name) {
        txtVehicleName.setText(name);
    }

    /**
     * Obtém o valor do lance inserido pelo usuário.
     * @return Valor do lance como double
     */
    public double getBidValue() {
        try {
            return Double.parseDouble(txtBidValue.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            System.err.println("Erro: Valor do lance inválido.");
            return 0.0;
        }
    }

    /**
     * Obtém o código do consórcio.
     * @return Código do consórcio inserido pelo usuário
     */
    public String getConsortiumCode() {
        return txtConsortiumCode.getText();
    }

    /**
     * Ação ao confirmar o lance.
     */
    private void confirmarLance() {
        double bidValue = getBidValue();
        String consortiumCode = getConsortiumCode();

        if (bidValue > 0 && !consortiumCode.isEmpty()) {
            System.out.println("Lance confirmado: R$" + bidValue + " para o consórcio " + consortiumCode);
        } else {
            System.err.println("Erro: Preencha todos os campos corretamente.");
        }
    }
}
