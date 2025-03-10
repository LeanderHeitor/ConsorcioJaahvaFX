package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Consorcio;
import com.example.consorciojaahvafx.model.Cliente;
import com.example.consorciojaahvafx.model.Grupo;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AbaConsorciosClienteController implements Initializable {

    @FXML
    private TableView<Grupo> tabelaConsorcios;

    @FXML
    private TableColumn<Consorcio, Long> colID;

    @FXML
    private TableColumn<Consorcio, String> colInicio, colDataSorteio;

    @FXML
    private TableColumn<Consorcio, Double> colValorRestante;

    @FXML
    private TableColumn<Consorcio, Boolean> colContemplado;

    @FXML
    private TableColumn<Consorcio, Integer> colParcelasPagas;

    @FXML
    private TableColumn<Consorcio, Double> colPremio;

    @FXML
    private Button btSair, btDarLance, btSimularLance;

    @FXML
    private Text tituloTela;

    private Cliente clienteLogado;

    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configColuna();
        carregarConsorcios();
    }

    public void setCliente(Cliente cliente) {
        this.clienteLogado = clienteLogado;
    }

    private void configColuna() {
        colID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        colInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataInicio().format(formatoData)));
        colDataSorteio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataInicio().format(formatoData)));
        //colValorRestante.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorRestante()));
        //colContemplado.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getContemplados()));
        //colParcelasPagas.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getParcelasPagas()));
        //colPremio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPremiacao()));
    }

    private void carregarConsorcios() {
        if (clienteLogado != null) {
            ArrayList<Grupo> consorcios = clienteLogado.getConsorciosAtivos();
            ObservableList<Grupo> listaConsorcios = FXCollections.observableArrayList(consorcios);
            tabelaConsorcios.setItems(listaConsorcios);
        }
    }

    private void simularLanceAction() {
        try{
            System.out.println(getClass().getResource("/abaClienteSimulacao.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/abaClienteSimulacao.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Simulação");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btSimularLance.getScene().getWindow();
            telaLogin.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void darLanceAction() {
        try{
            System.out.println(getClass().getResource("/abaClienteDarLance.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/abaClienteDarLance.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Dar Lance");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btDarLance.getScene().getWindow();
            telaLogin.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
