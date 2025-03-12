package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Cliente;
import com.example.consorciojaahvafx.model.Grupo;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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

import com.example.consorciojaahvafx.model.Consorcio;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbaAdminConsorcioInfo {

    @FXML
    private TableView<Grupo> tabelaConsorcios;

    @FXML
    private TableColumn<Consorcio, Long> colID;

    @FXML
    private TableColumn<Consorcio, String> colInicio;

    @FXML
    private TableColumn<Consorcio, LocalDate> colDataSorteio;

    @FXML
    private TableColumn<Consorcio, Double> colValorRestante, colPremio;

    @FXML
    private TableColumn<Consorcio, Boolean> colContemplado;

    @FXML
    private TableColumn<Consorcio, Integer> colParcelasPagas;

    @FXML
    private Button btSair;

    @FXML
    private Button btSortear;

    @FXML
    private Button btVoltar;

    private ObservableList<Consorcio> listaConsorcios;

    private Consorcio consorcio;

    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Cliente cliente;

    private void initialize(URL location, ResourceBundle resources) {
        configColuna();
        carregarConsorcios();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void configColuna() {
        colID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        colDataSorteio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataSorteio()));
        colDataSorteio.setCellFactory(column -> new TextFieldTableCell<>(new LocalDateStringConverter()));
        colValorRestante.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorRestante()).asObject());
        //colContemplado.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().isContemplado()));
        colParcelasPagas.setCellValueFactory(cellData -> {int totalParcelas = cellData.getValue().getParcelasPagas().values().stream().mapToInt(Integer::intValue).sum();return new SimpleIntegerProperty(totalParcelas).asObject();});
        //colPremio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPremiacao()));
    }

    private void carregarConsorcios() {
        if (cliente != null) {
            ArrayList<Grupo> consorcios = cliente.getConsorciosAtivos();
            ObservableList<Grupo> listaConsorcios = FXCollections.observableArrayList(consorcios);
            tabelaConsorcios.setItems(listaConsorcios);
        }
    }

    @FXML
    private void sortearConsorcioAction() {}

    @FXML
    private void voltarAction() {
        try{
            System.out.println(getClass().getResource("/AbaAdmin.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbaAdmin.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Administrador");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btVoltar.getScene().getWindow();
            telaLogin.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
