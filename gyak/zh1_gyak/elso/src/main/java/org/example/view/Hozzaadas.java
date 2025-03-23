package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.TelefonController;
import org.example.model.Telefon;


public class Hozzaadas {
    public Hozzaadas() {
        GridPane root = new GridPane();
        var scene = new Scene(root, 640, 480);
        Stage stage = new Stage();

        int sorIndex = 0;
        root.add(new Text("Név"), 0 , sorIndex);
        TextField nevTF = new TextField();
        root.add(nevTF, 1 , sorIndex);
        sorIndex++;


        //elemek hozzaadasa
        root.add(new Text("Gyártó"), 0 , sorIndex);
        ObservableList<String> markak = FXCollections.observableArrayList(
                "Apple", "Samsung", "Nokia", "Xiaomi","Huawei");

        ComboBox<String> gyartoCombo = new ComboBox<String>();

        gyartoCombo.setItems(markak); //értékek beállítása
        gyartoCombo.setValue(""); //default érték
        root.add(gyartoCombo, 1, sorIndex);
        sorIndex++;


        root.add(new Text("eSim"), 0 , sorIndex);
        CheckBox esimCB = new CheckBox();
        root.add(esimCB, 1, sorIndex);
        sorIndex++;


        Button ok = new Button("OK");
        root.add(ok, 0, sorIndex);
        Button megse = new Button("Megse");
        root.add(megse, 1, sorIndex);


        //gombok megvalositasa
        megse.setOnAction(a-> {
            stage.close();
        });

        ok.setOnAction(a-> {
            //nev ellenorzese
            StringBuilder hibak = new StringBuilder();
            if (nevTF.getCharacters().toString().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Állítsd be a nevet!");
                alert.showAndWait();
            } else {
                if (TelefonController.getInstance().add(new Telefon(
                        nevTF.getText(),
                        gyartoCombo.getValue(),
                        esimCB.isSelected()
                ))) {
                    stage.close();
                }
            }


        });


        stage.setScene(scene);
        stage.show();

    }


}
