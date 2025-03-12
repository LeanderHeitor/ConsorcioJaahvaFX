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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class AbaAdminClienteInfoController {

    @FXML
    private TextField tfNomeAdminInfo;

    @FXML
    private TextField tfCPFAdminInfo;

    @FXML
    private TextField tfTelefoneAdminInfo;

    @FXML
    private TextField tfEmailAdminInfo;

    @FXML
    private TextField tfTaxaAdmin;

    @FXML
    private TextField tfPenalidadeCliente;

    @FXML
    private Button btSair;

    @FXML
    private Button btAcessarGrupo;

    @FXML
    private Button btVoltarTelaAdmin;

    public void inicializarDadosCliente(String nome, String cpf, String telefone, String email, Double taxa ) {
        tfNomeAdminInfo.setText(nome);
        tfCPFAdminInfo.setText(cpf);
        tfTelefoneAdminInfo.setText(telefone);
        tfEmailAdminInfo.setText(email);
        tfTaxaAdmin.setText(Float.toString(Float.parseFloat(tfTaxaAdmin.getText())));
    }

    @FXML
    private void acessarGrupoAction() {
        String penalidade = tfPenalidadeCliente.getText();
        if (!penalidade.isEmpty()) {
            System.out.println("Penalidade aplicada ao cliente: " + penalidade);
            tfPenalidadeCliente.clear();

            //adicionar a lógica para somar a penalidade a parcela do cliente
        }
    }

    @FXML
    private void VoltarParaTelaDeAdminAction() {
        try{
            System.out.println(getClass().getResource("/AbaAdminGrupoInfo.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AbaAdminGrupoInfo.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Cliente");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btVoltarTelaAdmin.getScene().getWindow();
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
