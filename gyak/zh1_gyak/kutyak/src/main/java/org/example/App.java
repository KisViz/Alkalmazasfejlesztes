package org.example;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.KutyaController;
import org.example.model.Kutya;
import org.example.view.Felvitel;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Kutya");
        MenuItem felvitelMI = new MenuItem("Felvitel");
        menu.getItems().add(felvitelMI);
        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

        felvitelMI.setOnAction(a-> {
            new Felvitel();
        });


        TableView<Kutya> table = new TableView<>();
        ObservableList<Kutya> data = FXCollections.observableArrayList(KutyaController.getInstance().findAll());
        table.setItems(data);

        TableColumn<Kutya, String> nevColumn = new TableColumn<>("Név");
        nevColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));

        TableColumn<Kutya, String> fajtaColumn = new TableColumn<>("Fajta");
        fajtaColumn.setCellValueFactory(new PropertyValueFactory<>("faj"));

        TableColumn<Kutya, String> gazdaColumn = new TableColumn<>("Gazda");
        gazdaColumn.setCellValueFactory(new PropertyValueFactory<>("gazda"));

        table.getColumns().addAll(nevColumn, fajtaColumn, gazdaColumn);
        root.getChildren().add(table);




        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}