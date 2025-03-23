package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.JegyzokonyvController;
import org.example.dao.JegyzokonyvDAO;
import org.example.dao.JegyzokonyvDAOImpl;
import org.example.model.Jegyzokonyv;
import org.example.view.Felvitel;
import org.example.view.Segitseg;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        var scene = new Scene(root, 640, 480);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Jegyzőkönyv");
        MenuItem felvitel = new MenuItem("Felvitel");
        MenuItem segitseg = new MenuItem("Segítség");
        root.getChildren().add(menuBar);
        menuBar.getMenus().add(menu);
        menu.getItems().addAll(felvitel, segitseg);

        felvitel.setOnAction(a-> {
            new Felvitel();
        });

        segitseg.setOnAction(a-> {
            new Segitseg();
        });

        TableView<Jegyzokonyv> table = new TableView<>();

        TableColumn<Jegyzokonyv, String> cimColumn = new TableColumn<>("Cím");
        cimColumn.setCellValueFactory(new PropertyValueFactory<>("cim"));

        TableColumn<Jegyzokonyv, String> datumColumn = new TableColumn<>("Dátum");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

        table.getColumns().addAll(cimColumn, datumColumn);

        JegyzokonyvDAOImpl dao = new JegyzokonyvDAOImpl();
        ObservableList<Jegyzokonyv> jegyzokonyvs = FXCollections.observableArrayList(dao.find(new Jegyzokonyv()));
        table.setItems(jegyzokonyvs);

        TextField cimTF = new TextField("Cím");
        TextField leirasTF = new TextField("Leírás");
        TextField datumTF = new TextField("Dátum");
        TextField jegyzoTF = new TextField("Jegyző");
        Button kesesesB = new Button("Kereses");

        kesesesB.setOnAction(a-> {
            if (cimTF.getText().isBlank()) {
                jegyzokonyvs.setAll(dao.find(new Jegyzokonyv()));
            } else {
                jegyzokonyvs.setAll(dao.find(new Jegyzokonyv(cimTF.getText(),leirasTF.getText(),datumTF.getText(),jegyzoTF.getText())));
            }

        });

        table.setRowFactory( tv -> {
            var row = new TableRow<Jegyzokonyv>();
            row.setOnMouseClicked( click -> {
                var student = row.getItem(); // elkérhető a sorban tárolt objektum
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("Eredmények - " + student.getCim());
                VBox root1 = new VBox(new Text("Kapcsolodo információk: todo"));
                dialog.getDialogPane().setContent(root1);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
            });
            return row;
        });






        root.getChildren().addAll(new Text("Keresés"),cimTF,leirasTF,datumTF,jegyzoTF,kesesesB,table);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}