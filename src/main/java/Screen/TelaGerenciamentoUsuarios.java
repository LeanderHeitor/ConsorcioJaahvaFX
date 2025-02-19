package Screen;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaGerenciamentoUsuarios extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciamento de Usuários");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        // Tabela de usuários
        TableView<String> tabelaUsuarios = new TableView<>();
        TableColumn<String, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<String, String> colunaEmail = new TableColumn<>("E-mail");
        tabelaUsuarios.getColumns().addAll(colunaNome, colunaEmail);

        // Lista de usuários fictícia
        ObservableList<String> usuarios = FXCollections.observableArrayList(
                "João Lucas", "Maria Silva", "Carlos Souza"
        );
        tabelaUsuarios.setItems(usuarios);

        // Botões de ação
        Button btnAdicionar = new Button("Adicionar Usuário");
        Button btnEditar = new Button("Editar Usuário");
        Button btnRemover = new Button("Remover Usuário");

        btnAdicionar.setOnAction(e -> System.out.println("Adicionar Usuário"));
        btnEditar.setOnAction(e -> System.out.println("Editar Usuário"));
        btnRemover.setOnAction(e -> System.out.println("Remover Usuário"));

        layout.getChildren().addAll(tabelaUsuarios, btnAdicionar, btnEditar, btnRemover);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
