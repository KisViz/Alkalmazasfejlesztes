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
        UtazasController.getInstance().add(new Utazas("Tourist", "Szatymaz", true, 2, 2, "Jó hely"));
        UtazasController.getInstance().add(new Utazas("Narancs", "Makó", true, 2, 2, "Falu"));


        VBox root = new VBox();
        var scene = new Scene(root, 640, 480);


        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Utazások");
        MenuItem ujUtazas = new MenuItem("Új utazas");
        MenuItem utazasListazas = new MenuItem("Utazás listázás");

        menuBar.getMenus().add(menu);
        menu.getItems().addAll(ujUtazas, utazasListazas);
        root.getChildren().add(menuBar);

        ujUtazas.setOnAction(a -> {
            new UjUtazas();
        });

        utazasListazas.setOnAction(a-> {
            stage.setScene(new UtazasListazas(stage).getScene());
        });


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}