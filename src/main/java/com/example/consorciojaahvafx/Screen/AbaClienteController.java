package com.example.consorciojaahvafx.Screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.consorciojaahvafx.model.Usuario;
import com.example.consorciojaahvafx.model.Consorcio;
import com.example.consorciojaahvafx.model.Grupo;
import javafx.stage.Stage;

import java.io.IOException;

public class AbaClienteController  {

    @FXML
    private Label nomeCliente, cpfCliente, telefoneCliente, emailCliente;

    @FXML
    private TableView<Consorcio> tabelaGrupos;

    @FXML
    private TableColumn<Consorcio, String> colunaNomeGrupo;

    @FXML
    private TableColumn<Consorcio, Double> colunaValorTotal;

    @FXML
    private TableColumn<Consorcio, Integer> colunaParticipantes;

    @FXML
    private Button btAcessarConsorcio, btAcessarGrupo, btSair;

    private ObservableList<Consorcio> grupos = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        colunaNomeGrupo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colunaParticipantes.setCellValueFactory(new PropertyValueFactory<>("numParticipantes"));

        tabelaGrupos.setItems(grupos);
    }

    public void carregarCliente(String nome, String cpf, String telefone, String email) {
        nomeCliente.setText(nome);
        cpfCliente.setText(cpf);
        telefoneCliente.setText(telefone);
        emailCliente.setText(email);
    }
    public void carregarGrupo(ObservableList<Consorcio> grupos) {
        this.grupos.setAll(grupos);
    }

    public void abrirTelaDeConsorcio() {
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

    @FXML
    private void sairButtonAction() {
        System.exit(0);
    }


}
