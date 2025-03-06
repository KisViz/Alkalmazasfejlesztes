package hu.alkfejl;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.view.UjUtazas;
import hu.alkfejl.view.UtazasListazas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        UtazasController.getInstance().add(new Utazas("utazas1", "Roma", true, 2, 2, "nincs"));
        UtazasController.getInstance().add(new Utazas("utazas2", "Nemroma", true, 2, 2, "nincs"));


        VBox vb = new VBox();
//        vb.getChildren().add(new Text("Utazasok"));

        MenuBar menuBar = new MenuBar();
        Menu utazasok = new Menu("Utazás");
        MenuItem felvitel = new MenuItem("Felvitel");

        felvitel.setOnAction( a -> {
            new UjUtazas();
        });

        MenuItem utazasListazas = new MenuItem("Listázás");
        utazasListazas.setOnAction(a -> {
            stage.setScene(new UtazasListazas(stage).getScene());
        });

        utazasok.getItems().addAll(felvitel, utazasListazas);
        menuBar.getMenus().addAll(utazasok);
        vb.getChildren().addAll(menuBar);

        var scene = new Scene(vb, 640, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}