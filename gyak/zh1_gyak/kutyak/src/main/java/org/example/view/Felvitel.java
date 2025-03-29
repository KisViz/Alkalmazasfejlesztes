package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.KutyaController;
import org.example.model.Kutya;


public class Felvitel {
    public Felvitel() {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        Scene scene  =new Scene(root, 640,480);

        Button megse = new Button("Megse");
        Button mentes = new Button("Mentes");


        TextField nevTF = new TextField();

        ObservableList<String> fajtak = FXCollections.observableArrayList(
                "Tacskó","Németjuhász","Agár", "Akita");

        ComboBox<String> fajtaCombo = new ComboBox<String>();

        fajtaCombo.setItems(fajtak); //értékek beállítása

        TextField gazdiTF = new TextField();


        root.addColumn(0, new Text("Név"), new Text("Fajta"), new Text("Gazdi"), megse);
        root.addColumn(1, nevTF, fajtaCombo, gazdiTF, mentes);

        megse.setOnAction(a-> {
            stage.close();
        });

        mentes.setOnAction(a-> {
            StringBuilder hibak = new StringBuilder();
            if (nevTF.getText().isBlank()) {
                hibak.append("Add meg a nevet!\n");
            }
            if (fajtaCombo.getValue() == null) {
                hibak.append("Add meg a fatát!\n");
            }
            if (gazdiTF.getText().isBlank()) {
                hibak.append("Add meg a gazdát!\n");
            }

            if (hibak.toString().isBlank()) {
                KutyaController.getInstance().add(new Kutya(
                        nevTF.getText(),
                        fajtaCombo.getValue(),
                        gazdiTF.getText()
                ));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Hibák");
                alert.setContentText(hibak.toString());
                alert.showAndWait();
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
