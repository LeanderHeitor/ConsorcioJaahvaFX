package Screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginAdminController {

    @FXML
    private TextField adminName;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button voltarButton;

    @FXML
    private Button sairButton;

    @FXML
    private TextField mensagemText;

    public void initialize() {}

    private void handleLoginButtonAction() {
        String username = adminName.getText();
        String password = adminPassword.getText();

        if (username.equals("cliente") && password.equals("123456")) {
            mensagemText.setText("Login efetuado com sucesso!");
        } else {
            mensagemText.setText("Usuário ou senha incorretos!");
        }
    }

    private void handleSairButtonAction() {
        System.exit(0);
    }

    private void handleVoltarButtonAction() {}

}
