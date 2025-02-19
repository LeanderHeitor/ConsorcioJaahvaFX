package Screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaCadastroUsuario extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Usuário");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Campos do formulário
        Label nomeLabel = new Label("Nome:");
        TextField nomeInput = new TextField();

        Label cpfLabel = new Label("CPF:");
        TextField cpfInput = new TextField();

        Label telefoneLabel = new Label("Telefone:");
        TextField telefoneInput = new TextField();

        Label emailLabel = new Label("E-mail:");
        TextField emailInput = new TextField();

        Label senhaLabel = new Label("Senha:");
        PasswordField senhaInput = new PasswordField();

        Button cadastrarButton = new Button("Cadastrar");
        cadastrarButton.setOnAction(e -> {
            // Aqui você pode adicionar a lógica para salvar os dados
            System.out.println("Usuário cadastrado: " + nomeInput.getText());
        });

        // Adicionando os componentes ao layout
        grid.add(nomeLabel, 0, 0);
        grid.add(nomeInput, 1, 0);
        grid.add(cpfLabel, 0, 1);
        grid.add(cpfInput, 1, 1);
        grid.add(telefoneLabel, 0, 2);
        grid.add(telefoneInput, 1, 2);
        grid.add(emailLabel, 0, 3);
        grid.add(emailInput, 1, 3);
        grid.add(senhaLabel, 0, 4);
        grid.add(senhaInput, 1, 4);
        grid.add(cadastrarButton, 1, 5);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
