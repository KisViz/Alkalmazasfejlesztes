package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.view.Hozzaadas;
import org.example.view.Listazas;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();

        //menu letrehozasa
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menü");
        MenuItem hozzaadasMI = new MenuItem("Hozzáadás");
        MenuItem listaszasMI = new MenuItem("Listázás");

        menuBar.getMenus().add(menu);
        menu.getItems().addAll(hozzaadasMI,listaszasMI);
        root.getChildren().add(menuBar);


        //hozzaadas
        hozzaadasMI.setOnAction(a-> {
            new Hozzaadas();
        });
        //listazas
        listaszasMI.setOnAction(a-> {
            stage.setScene(new Listazas(stage).getScene());
        });


        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}