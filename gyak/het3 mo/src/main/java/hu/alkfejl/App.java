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
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        UtazasController.getInstance().add(new Utazas("utazas1", "Roma", true, 2, 2, "nincs"));
        UtazasController.getInstance().add(new Utazas("utazas2", "Nemroma", true, 2, 2, "nincs"));

        Menu utazasokMenu = new Menu("Utazasok");
        MenuItem hozzaadasMi = new MenuItem("Hozzaadas");
        hozzaadasMi.setOnAction(e -> {
            new UjUtazas();
        });

        MenuItem listazasMI = new MenuItem("Lstázás");
        listazasMI.setOnAction(
                e-> {

                    stage.setScene(new UtazasListazas(stage).getScene());
                }
        );
        utazasokMenu.getItems().addAll(hozzaadasMi, listazasMI);

        MenuBar menuBar = new MenuBar(utazasokMenu);

        VBox vb = new VBox();
        vb.getChildren().add(menuBar);

        var scene = new Scene(vb, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Utazasok");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}