package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AbaGrupoClienteController implements Initializable {

    @FXML
    private TextField tfGrupo, tfID, tfParcelasPagas, tfValorTotal, tfSupervisor, tfTaxaSupervisor, tfValorAtualizado;

    @FXML
    private TableView<?> tabelaParticipantes;

    @FXML
    private TableColumn<?, ?> colNome, colCPF, colTelefone, colEmail, colID;

    @FXML
    private Button btVoltarTelaCliente, btSair;

    @FXML
    private void VoltarTelaClienteAction() {
        Stage stage = (Stage) btVoltarTelaCliente.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializar valores e configurar a tabela aqui, se necessário
    }

    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }
}
