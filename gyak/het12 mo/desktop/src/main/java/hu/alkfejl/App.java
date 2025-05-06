package hu.alkfejl;

import hu.alkfejl.controller.LatnivaloController;
import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Latnivalo;
import hu.alkfejl.model.Utazas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static String lastScene;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        lastScene = fxml;
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static String getLastScene() {
        return lastScene;
    }

    public static void main(String[] args) {
        launch();
    }

}