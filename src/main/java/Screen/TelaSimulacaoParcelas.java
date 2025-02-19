package Screen;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaSimulacaoParcelas extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulação de Parcelas");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Label titulo = new Label("Simule suas parcelas");
        Label valorLabel = new Label("Valor Total do Consórcio:");
        TextField valorInput = new TextField();

        Label participantesLabel = new Label("Número de Participantes:");
        TextField participantesInput = new TextField();

        Label taxaLabel = new Label("Taxa de Administração (%):");
        TextField taxaInput = new TextField();

        Button btnCalcular = new Button("Calcular Parcelas");
        Label resultadoLabel = new Label();

        btnCalcular.setOnAction(e -> {
            try {
                double valor = Double.parseDouble(valorInput.getText());
                int participantes = Integer.parseInt(participantesInput.getText());
                double taxa = Double.parseDouble(taxaInput.getText());

                double parcela = (valor + (valor * (taxa / 100))) / participantes;
                resultadoLabel.setText("Valor da parcela: R$ " + String.format("%.2f", parcela));
            } catch (NumberFormatException ex) {
                resultadoLabel.setText("Erro: Insira valores numéricos válidos.");
            }
        });

        layout.getChildren().addAll(titulo, valorLabel, valorInput, participantesLabel, participantesInput, taxaLabel, taxaInput, btnCalcular, resultadoLabel);

        Scene scene = new Scene(layout, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
