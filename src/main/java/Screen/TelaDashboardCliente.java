package Screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaDashboardCliente extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard do Cliente");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Label titulo = new Label("Bem-vindo ao seu painel, Cliente!");
        Label saldoDevedor = new Label("Saldo Devedor: R$ 15.000,00");
        Label statusContrato = new Label("Status do Contrato: Ativo");

        Button btnSimularParcelas = new Button("Simular Parcelas");
        Button btnVerPagamentos = new Button("Histórico de Pagamentos");
        Button btnConsultarContemplacoes = new Button("Consultar Contemplações");

        btnSimularParcelas.setOnAction(e -> System.out.println("Ir para Simulação de Parcelas"));
        btnVerPagamentos.setOnAction(e -> System.out.println("Ir para Histórico de Pagamentos"));
        btnConsultarContemplacoes.setOnAction(e -> System.out.println("Ir para Consulta de Contemplações"));

        layout.getChildren().addAll(titulo, saldoDevedor, statusContrato, btnSimularParcelas, btnVerPagamentos, btnConsultarContemplacoes);

        Scene scene = new Scene(layout, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}