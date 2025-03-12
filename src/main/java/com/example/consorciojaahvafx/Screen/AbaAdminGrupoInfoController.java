package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Grupo;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AbaAdminGrupoInfoController {

    @FXML
    private TableView<Grupo> tabelaGrupos;

    @FXML
    private TableColumn<Grupo, Long> colIDAdminInfo;

    @FXML
    private TableColumn<Grupo, Integer> colParcelasAdminInfo;

    @FXML
    private TableColumn<Grupo, Double> colValorTotalAdminInfo;

    @FXML
    private TableColumn<Grupo, String> colAdminAdminInfo;

    @FXML
    private TableColumn<Grupo, Double> colTaxaAdminInfo;

    @FXML
    private Button btAcessarGrupoAdminInfoCliente;

    @FXML
    private Button btVoltarTelaAdminInfoCliente;

    @FXML
    private Button btSair;

    private ObservableList<Grupo> listaGrupos = FXCollections.observableArrayList();

    private Grupo grupo;

    public void initialize() {
        carregarGrupo();
        configurarColunas();
    }


    public void setarGrupos(Grupo grupo){this.grupo = grupo;}

    public void carregarGrupo() {
        if (grupo != null) {
            List<Grupo> grupoList = grupo.getParticipantes();
            ObservableList<Grupo> listarParticipantesGrupos = FXCollections.observableArrayList(grupoList);
            tabelaGrupos.setItems(listarParticipantesGrupos);
        }
    }

    public void configurarColunas() {
        colAdminAdminInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupervisor().getNome()));
        colIDAdminInfo.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        colValorTotalAdminInfo.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorTotal()).asObject());
        colParcelasAdminInfo.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getValorTotal()).asObject());
        colTaxaAdminInfo.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTaxaAdm()).asObject());
    }


    public void acessarGrupoAdminInfoClienteAction () {}

    public void voltarAdminInfoClienteAction () {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AbaAdmin.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) btVoltarTelaAdminInfoCliente.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void FinalizarPenalidadeClienteAction(){}

    @FXML
    private void sairButtonAdminInfoClienteAction() {
        System.exit(0);
    }

}
