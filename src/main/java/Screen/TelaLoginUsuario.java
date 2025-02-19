package Screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaLoginUsuario extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login de Usuário");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Campos do formulário
        Label emailLabel = new Label("E-mail:");
        TextField emailInput = new TextField();

        Label senhaLabel = new Label("Senha:");
        PasswordField senhaInput = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            // Aqui você pode adicionar a lógica para validar o login
            System.out.println("Tentativa de login com: " + emailInput.getText());
        });

        // Adicionando os componentes ao layout
        grid.add(emailLabel, 0, 0);
        grid.add(emailInput, 1, 0);
        grid.add(senhaLabel, 0, 1);
        grid.add(senhaInput, 1, 1);
        grid.add(loginButton, 1, 2);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
