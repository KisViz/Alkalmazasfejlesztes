package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.dao.MobilDAO;
import org.example.dao.MobilDAOImpl;
import org.example.model.Mobil;
import org.example.view.Hozzaadas;

import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        var scene = new Scene(root, 640, 480);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menü");
        MenuItem hozzaadasMI = new MenuItem("Hozzáadás");
        MenuItem listazasMI = new MenuItem("Listázás");
        menu.getItems().addAll(hozzaadasMI, listazasMI);
        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

        hozzaadasMI.setOnAction(a-> {
            new Hozzaadas();
        });

        TableView<Mobil> table = new TableView<>();

        TableColumn<Mobil, String> nevColumn = new TableColumn<>("Név");
        nevColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));

        TableColumn<Mobil, Integer> gyartoColumn = new TableColumn<>("Gyártó");
        gyartoColumn.setCellValueFactory(new PropertyValueFactory<>("gyarto"));

        TableColumn<Mobil, Integer> esimColumn = new TableColumn<>("eSim");
        esimColumn.setCellValueFactory(new PropertyValueFactory<>("esim"));

        table.getColumns().addAll(nevColumn, gyartoColumn, esimColumn);

        MobilDAO dao = new MobilDAOImpl();
        ObservableList<Mobil> mobilok = FXCollections.observableArrayList(dao.find(new Mobil()));
        table.setItems(mobilok);

        listazasMI.setOnAction(a-> {
            ObservableList<Mobil> mobilok2 = FXCollections.observableArrayList(dao.find(new Mobil()));
            table.setItems(mobilok2);
        });

        root.getChildren().add(table);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}