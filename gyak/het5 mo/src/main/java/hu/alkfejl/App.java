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
    /**
     * Csak nehany teszt adat a listazashoz
     * */
    private static void addData() {
        var uc = UtazasController.getInstance();
        uc.add( new Utazas("Mexico1", "Mexico", true, 2, 7, "Long"));
        uc.add( new Utazas("Mexico2", "Mexico", false, 3, 14, "Longer"));
        uc.add( new Utazas("Roma1", "Roma", true, 2, 5, "Not Long"));

        var lc = LatnivaloController.getInstance();
        lc.add(new Latnivalo("Latnivalo1", 5, 4, 5, "Description\nMultiline description a little longer line too", 5));
        lc.add(new Latnivalo("Latnivalo2", 5000, 16, 17, "Description", 4));
    }
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        addData();
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