package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GrupoCardController {

    @FXML
    private Text txtGroupName;

    @FXML
    private Text txtValue;

    @FXML
    private ImageView imgGroup;

    @FXML
    private Button btnIncrease;

    private double value = 0.0;

    /**
     * Define o nome do grupo de consórcio exibido no cartão.
     * @param name Nome do grupo
     */
    public void setGroupName(String name) {
        txtGroupName.setText(name);
    }

    /**
     * Define o valor exibido no cartão.
     * @param value Novo valor
     */
    public void setValue(double value) {
        this.value = value;
        updateValueText();
    }

    /**
     * Define a imagem do grupo.
     * @param imagePath Caminho da imagem
     */
    public void setImage(String imagePath) {
        imgGroup.setImage(new Image(imagePath));
    }

    /**
     * Atualiza o texto do valor formatado.
     */
    private void updateValueText() {
        txtValue.setText(String.format("%.2f", value));
    }

    /**
     * Ação ao clicar no botão "+", aumentando o valor.
     */
    @FXML
    private void increaseValue() {
        value += 10.0;
        updateValueText();
    }
}
