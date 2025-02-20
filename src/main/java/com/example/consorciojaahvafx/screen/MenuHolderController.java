package com.example.consorciojaahvafx.screen;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuHolderController {

    @FXML
    private AnchorPane menuHolder;

    @FXML
    private void initialize() {
        // Configurações iniciais, caso necessário.
        System.out.println("Menu Principal Carregado.");
    }

    // Método de exemplo para adicionar futuras interações ou manipulação do layout
    public void setMenuStyle(String style) {
        menuHolder.setStyle(style);
    }
}
