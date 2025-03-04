package Screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import controller.Fachada;

public class Application extends javafx.application.Application {
    private Fachada server;

    public Application() {
        this.server = Fachada.getInstance();
    }

    public Fachada getServer() {
        return server;
    }

    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStg(stage);
        System.out.println(getClass().getResource("/resources/Login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/resources/Login.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/screen/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("JaahConsorcio");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}