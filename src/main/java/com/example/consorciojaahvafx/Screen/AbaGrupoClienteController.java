package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Cliente;
import com.example.consorciojaahvafx.model.Grupo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

public class AbaGrupoClienteController implements Initializable {

    @FXML
    private TextField tfGrupo, tfID, tfParcelasPagas, tfValorTotal, tfSupervisor, tfTaxaSupervisor, tfValorAtualizado;

    @FXML
    private TableView<Grupo> tabelaParticipantes;

    @FXML
    private TableColumn<Cliente, String> colNome, colCPF, colTelefone, colEmail;

    @FXML
    private TableColumn<Cliente, Long> colId;

    @FXML
    private Button btVoltarTelaCliente, btSair;

    private Grupo grupoSelecionado;

    private Cliente cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencherCamposGrupo();
        configColunas();
        carregarParticipantes();
    }

    public void setarGrupo(Grupo grupo) {
        this.grupoSelecionado = grupo;
    }

    private void preencherCamposGrupo() {
        if (grupoSelecionado != null) {
            tfGrupo.setText(grupoSelecionado.getNome());
            tfID.setText(grupoSelecionado.getId().toString());
            tfParcelasPagas.setText(String.valueOf(grupoSelecionado.getNumeroParcelas()));
            tfValorTotal.setText(String.valueOf(grupoSelecionado.getValorTotal()));
            tfSupervisor.setText(grupoSelecionado.getSupervisor().getNome());
            tfTaxaSupervisor.setText(String.valueOf(grupoSelecionado.getTaxaAdm()));
            tfValorAtualizado.setText(String.valueOf(grupoSelecionado.getValorTotal()));
        }
    }

    private void carregarParticipantes() {
        if (grupoSelecionado != null) {
            List<Grupo> participantes = grupoSelecionado.getParticipantes();
            ObservableList<Grupo> listaParticipantes = FXCollections.observableList(participantes);
            tabelaParticipantes.setItems(listaParticipantes);
        }
    }

    private void configColunas() {
        colNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colCPF.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCPF()));
        colTelefone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colId.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
    }

    @FXML
    private void VoltarTelaClienteAction() {
        try{
            System.out.println(getClass().getResource("/abaCliente.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/abaCliente.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Cliente");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btVoltarTelaCliente.getScene().getWindow();
            telaLogin.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }
}
