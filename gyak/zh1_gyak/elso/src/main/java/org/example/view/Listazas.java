package org.example.view;

import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.dao.ContactDAOImpl;
import org.example.model.Telefon;

//itt most nem uj ablakban szeretnem megnyitni, nehogy ilyen kelljen zhban
public class Listazas {
    Scene scene;

    public Scene getScene() {
        return scene;
    }

    public Listazas(Stage stage) {
        Scene regi = stage.getScene();

        VBox root = new VBox();

        //vissza gomb
        Button vissza = new Button("Vissza");
        root.getChildren().add(vissza);
        vissza.setOnAction(a-> {
            stage.setScene(regi);
        });


        //tablazat letrehozasa
        TableView<Telefon> table = new TableView<>();


        //oszlopok letrehozasa
        TableColumn<Telefon, String> nevColumn = new TableColumn<>("Név");
        nevColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));

        TableColumn<Telefon, Integer> gyartoColumn = new TableColumn<>("Gyártó");
        gyartoColumn.setCellValueFactory(new PropertyValueFactory<>("gyarto"));

        TableColumn<Telefon, Boolean> esimColumn = new TableColumn<>("eSim");

        esimColumn.setCellFactory( CheckBoxTableCell.forTableColumn(esimColumn) );
        esimColumn.setCellValueFactory(cellData -> {
            Telefon item = cellData.getValue();
            SimpleBooleanProperty property = new SimpleBooleanProperty(item.isEsim());
            // kell ez a property, hogy ne csak TRUE/FALSE legyen a tablazat kiiratasa. Az alap PropertyValueFactory TRUE/FALSE-ra rakna
            return property;
        });

        table.getColumns().addAll(nevColumn, gyartoColumn, esimColumn);


        //sorok letrehozasa
        ContactDAOImpl dao = new ContactDAOImpl();
        ObservableList<Telefon> telefonok = FXCollections.observableArrayList(dao.find(new Telefon()));
        table.setItems(telefonok);

        root.getChildren().add(table);

        scene = new Scene(root, 600, 480);
    }
}
