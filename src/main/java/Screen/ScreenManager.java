package Screen;

import exception.ArquivosDeTelaException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stage;


    //telas como atributos associados a um controller
    private Scene menuScene;
    private MenuHolderController menuHolderController;


    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    public ScreenManager(){
        stage = new Stage();
        this.carregarTelas();
    }

    private void carregarTelas(){
        //Carregar telas
        try {
            FXMLLoader MenuLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            this.menuScene = new Scene(MenuLoader.load());
            this.menuHolderController = MenuLoader.getController();

        } catch (Exception e) {
            throw new ArquivosDeTelaException("Erro ao carregar arquivos de tela");
        }
    }

    public void mudarTela(String fxml){
        String titulo = null;
        switch (fxml){

            case "MainMenu.fxml":
                stage.setScene(menuScene);
                stage.getScene().getStylesheets().add(getClass().getResource("MainMenu.css").toExternalForm());
                titulo = "Menu Principal";
                break;
            case "Tela2.fxml":
                //Carregar tela 2
                break;
        }
        stage.setTitle(titulo);
    }
}

