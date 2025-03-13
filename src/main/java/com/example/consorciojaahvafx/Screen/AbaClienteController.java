package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.*;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AbaClienteController  {

    @FXML
    private Label lbNome, lbCPF, lbTelefone, lbEmail;

    @FXML
    private TextField tfNome, tfCPF, tfTelefone, tfEmail;

    @FXML
    private TableView<Cliente> tabelaGrupos;

    @FXML
    private TableColumn<Grupo, String> colAdmin;

    @FXML
    private TableColumn<Grupo, Long> colID;

    @FXML
    private TableColumn<Grupo, Double> colValorTotal;

    @FXML
    private TableColumn<Grupo, Integer> colParcelas;

    @FXML
    private Button btAcessarConsorcio, btAcessarGrupo, btSair;

    private ObservableList<Consorcio> grupos = FXCollections.observableArrayList();

    private Cliente cliente;

    private Grupo grupo;

    @FXML
    void initialize() {
        carregarCliente();
        carregarGrupo();
        configurarColunas();

    }

    public void setarCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setarGrupos(Grupo grupo) {
        this.grupo = grupo;
    }

    public void carregarCliente() {
        if (cliente != null) {
            tfNome.setText(cliente.getNome());
            tfCPF.setText(cliente.getCPF());
            tfTelefone.setText(cliente.getTelefone());
            tfEmail.setText(cliente.getEmail());
        }
    }
    public void carregarGrupo() {
        if (grupo != null) {
            ArrayList<Cliente> grupoList = grupo.getParticipantes();
            ObservableList<Cliente> listarParticipantesGrupos = FXCollections.observableArrayList(grupoList);
            tabelaGrupos.setItems(listarParticipantesGrupos);
        }
    }

    public void configurarColunas() {
        colAdmin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupervisor().getNome()));
        colID.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        colValorTotal.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorTotal()).asObject());
        colParcelas.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getValorTotal()).asObject());
    }


    public void acessarConsorciosAction() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ConsorciosCliente.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Consórcios");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) btAcessarConsorcio.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acessarGrupoAction() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AbaGrupoCliente.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Grupos");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) btAcessarGrupo.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }


}
