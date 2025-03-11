package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AbaAdminController {

    @FXML
    private TextField tfIDAdmin;

    @FXML
    private TextField tfNomeAdmin;

    @FXML
    private TextField tfCPFAdmin;

    @FXML
    private TextField tfTelefoneAdmin;

    @FXML
    private TextField tfEmailAdmin;


    @FXML
    private Button btAcessarInfoGrupo;

    @FXML
    private Button btAcessarInfoConsorcio;

    @FXML
    private Button btSair;

    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private void preencherCampos () {
        if (admin != null) {
            tfNomeAdmin.setText(admin.getNome());
            tfCPFAdmin.setText(admin.getCPF());
            tfTelefoneAdmin.setText(admin.getTelefone());
            tfEmailAdmin.setText(admin.getEmail());
            tfIDAdmin.setText(String.valueOf(admin.getId()));
        }
    }


    @FXML
    private void acessarGrupoAction () {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AbaAdminGrupoInfo.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Grupo");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) btAcessarInfoGrupo.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void acessarConsorcioAction() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AbaAdminConsorcioInfo.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Consorcio");
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) btAcessarInfoConsorcio.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sairButtonAction() {
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.close();
    }

    private void abrirNovaTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
