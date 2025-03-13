package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Cliente;
import com.example.consorciojaahvafx.model.Grupo;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AbaAdminGrupoInfoDetalhesController {

    @FXML
    private TextField tfGrupoAdminInfo;

    @FXML
    private TextField tfIDAdminInfo;

    @FXML
    private TextField tfParcelasPagasAdminInfo;

    @FXML
    private TextField tfValorTotalAdminInfo;

    @FXML
    private TextField tfTaxaSupervisorAdminInfo;

    @FXML
    private TextField tfValorAtualizadoAdminInfo;

    @FXML
    private TableView<Cliente> tabelaParticipantes;

    @FXML
    private TableColumn<Cliente, String> colNomeClienteAdminInfo, colCPFClienteAdminInfo, colTelefoneClienteAdminInfo, colEmailClienteAdminInfo;


    @FXML
    private TableColumn<Cliente, Long> colIdClienteAdminInfo;

    @FXML
    private Button btAcessarClieteAdminInfo;

    @FXML
    private Button btVoltarTelaClienteAdminInfo;

    @FXML
    private Button btSair;

    private Grupo grupoSelecionado;

    public void initialize() {
        preencherCamposGrupo();
        carregarParticipantes();
        configColunas();

    }

    public void setarGrupo(Grupo grupo) {
        this.grupoSelecionado = grupo;
    }

    private void preencherCamposGrupo() {
        if (grupoSelecionado != null) {
            tfGrupoAdminInfo.setText(grupoSelecionado.getNome());
            tfIDAdminInfo.setText(grupoSelecionado.getId().toString());
            tfParcelasPagasAdminInfo.setText(String.valueOf(grupoSelecionado.getNumeroParcelas()));
            tfValorTotalAdminInfo.setText(String.valueOf(grupoSelecionado.getValorTotal()));
            tfTaxaSupervisorAdminInfo.setText(String.valueOf(grupoSelecionado.getTaxaAdm()));
            tfValorAtualizadoAdminInfo.setText(String.valueOf(grupoSelecionado.getValorTotal()));
        }
    }

    private void carregarParticipantes() {
        if (grupoSelecionado != null) {
            ArrayList<Cliente> participantes = grupoSelecionado.getParticipantes();
            ObservableList<Cliente> listaParticipantes = FXCollections.observableList(participantes);
            tabelaParticipantes.setItems(listaParticipantes);
        }
    }

    private void configColunas() {
        colNomeClienteAdminInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colCPFClienteAdminInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCPF()));
        colTelefoneClienteAdminInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));
        colEmailClienteAdminInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
    }

    @FXML
    private void VoltarTelaAdminInfoAction() {
        try{
            System.out.println(getClass().getResource("/abaAdmin.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/abaAdmin.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Administrador");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btVoltarTelaClienteAdminInfo.getScene().getWindow();
            telaLogin.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void AcessarClienteAdminInfoAction() {
        try{
            System.out.println(getClass().getResource("/abaAdminClienteInfo.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/abaAdminClienteInfo.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Cliente");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btAcessarClieteAdminInfo.getScene().getWindow();
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
