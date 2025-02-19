package Screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaPerfilUsuario extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Perfil do Usuário");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Campos do perfil
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

        Button salvarButton = new Button("Salvar Alterações");
        salvarButton.setOnAction(e -> {
            // Aqui você pode adicionar a lógica para salvar as alterações do usuário
            System.out.println("Perfil atualizado: " + nomeInput.getText());
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
        grid.add(salvarButton, 1, 5);

        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
