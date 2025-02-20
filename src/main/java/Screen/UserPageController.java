package Screen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UserPageController {

    @FXML
    private ImageView userImage;

    @FXML
    private TextField searchField;

    @FXML
    private Button homeButton, contractsButton, editButton, searchButton;

    @FXML
    private CheckBox groupsCheckBox, installmentsCheckBox;

    @FXML
    private void initialize() {
        // Inicializações de elementos, como configurar eventos para os botões
        homeButton.setOnAction(event -> handleHomeAction());
        contractsButton.setOnAction(event -> handleContractsAction());
        editButton.setOnAction(event -> handleEditAction());
        searchButton.setOnAction(event -> handleSearchAction());
    }

    // Ação para o botão Home
    private void handleHomeAction() {
        System.out.println("Acessando a página inicial...");
        // Lógica de navegação ou outra ação
    }

    // Ação para o botão Contratos
    private void handleContractsAction() {
        System.out.println("Visualizando contratos...");
        // Lógica para visualizar contratos
    }

    // Ação para o botão Editar
    private void handleEditAction() {
        System.out.println("Abrindo edição de perfil...");
        // Lógica para editar perfil
    }

    // Ação para o botão de Pesquisa
    private void handleSearchAction() {
        String searchQuery = searchField.getText();
        System.out.println("Buscando por: " + searchQuery);
        // Lógica para realizar a busca
    }

    // Ações para os CheckBoxes (Grupos, Parcelas)
    @FXML
    private void handleGroupsCheckBox() {
        if (groupsCheckBox.isSelected()) {
            System.out.println("Exibindo grupos.");
            // Lógica para exibir grupos
        } else {
            System.out.println("Ocultando grupos.");
            // Lógica para ocultar grupos
        }
    }

    @FXML
    private void handleInstallmentsCheckBox() {
        if (installmentsCheckBox.isSelected()) {
            System.out.println("Exibindo parcelas.");
            // Lógica para exibir parcelas
        } else {
            System.out.println("Ocultando parcelas.");
            // Lógica para ocultar parcelas
        }
    }
}
