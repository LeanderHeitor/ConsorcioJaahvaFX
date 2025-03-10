package com.example.consorciojaahvafx.Screen;

import com.example.consorciojaahvafx.model.Consorcio;
import com.example.consorciojaahvafx.model.Grupo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AbaClienteSimulacaoController {

    @FXML
    private TextField tfValorRestante;

    @FXML
    private TextField tfTaxaAdmin;

    @FXML
    private TextField tfValorSimulacao;

    @FXML
    private Button btFinalizarSimulacao;

    @FXML
    private Button btVoltarParaTelaDeConsorcios;

    @FXML
    private Button btSair;

    private Consorcio consorcioSelecionado;

    private Grupo grupoSelecionado;

    public void setConsorcio(Consorcio consorcio) {
        this.consorcioSelecionado = consorcio;
        preencherCampos();
    }
    public void setGrupo(Grupo grupo) {
        this.grupoSelecionado = grupo;
    }

    private void preencherCampos() {
        if (consorcioSelecionado != null) {
            tfValorRestante.setText(String.format("R$ %.2f", consorcioSelecionado.getValorRestante()));
            tfTaxaAdmin.setText(String.format("%.2f%%", grupoSelecionado.getTaxaAdm()));
        }
    }

    @FXML
    private void finalizarSimulacao() {
        try {
            double valorSimulado = Double.parseDouble(tfValorSimulacao.getText());
            double taxa = grupoSelecionado.getTaxaAdm() / 100;
            double valorComTaxa = valorSimulado + (valorSimulado * taxa);

            // Exibir resultado da simulação
            System.out.println("Valor simulado: R$ " + valorSimulado);
            System.out.println("Valor final com taxa: R$ " + valorComTaxa);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Insira um valor válido para a simulação.");
        }
    }

    @FXML
    private void voltarParaTelaDeConsorcios() {
        try{
            System.out.println(getClass().getResource("/abaConsorciosCliente.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/abaConsorciosCliente.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Cliente");
            stage.setScene(scene);
            stage.show();

            Stage telaLogin = (Stage) btVoltarParaTelaDeConsorcios.getScene().getWindow();
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
