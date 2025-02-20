package com.example.consorciojaahvafx.screen;

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
        homeButton.setOnAction(event -> handleHomeAction());
        contractsButton.setOnAction(event -> handleContractsAction());
        editButton.setOnAction(event -> handleEditAction());
        searchButton.setOnAction(event -> handleSearchAction());
    }

    private void handleHomeAction() {
        System.out.println("Acessando a página inicial...");
    }

    private void handleContractsAction() {
        System.out.println("Visualizando contratos...");
    }

    private void handleEditAction() {
        System.out.println("Abrindo edição de perfil...");
    }


    private void handleSearchAction() {
        String searchQuery = searchField.getText();
        System.out.println("Buscando por: " + searchQuery);
    }


    @FXML
    private void handleGroupsCheckBox() {
        if (groupsCheckBox.isSelected()) {
            System.out.println("Exibindo grupos.");
        } else {
            System.out.println("Ocultando grupos.");
        }
    }

    @FXML
    private void handleInstallmentsCheckBox() {
        if (installmentsCheckBox.isSelected()) {
            System.out.println("Exibindo parcelas.");
        } else {
            System.out.println("Ocultando parcelas.");
        }
    }
}
