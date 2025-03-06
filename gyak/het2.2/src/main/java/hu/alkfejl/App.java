package hu.alkfejl;

import hu.alkfejl.view.UjUtazas;
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
        VBox vb = new VBox();
//        vb.getChildren().add(new Text("Utazasok"));

        var scene = new Scene(vb, 640, 480);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Utazas");
        MenuItem felvitel = new MenuItem("Felvitel");

        menuBar.getMenus().addAll(menu);
        menu.getItems().add(felvitel);
        vb.getChildren().add(menuBar);

        felvitel.setOnAction(
                e -> {
                    System.out.println("asd");
                    new UjUtazas();
                }
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}