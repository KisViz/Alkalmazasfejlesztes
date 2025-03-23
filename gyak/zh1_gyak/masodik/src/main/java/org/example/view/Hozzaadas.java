package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.MobilController;
import org.example.model.Mobil;

public class Hozzaadas {
    public Hozzaadas() {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 600, 480);

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
            if (nevTF.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bajsz");
                alert.setHeaderText("Ajjaj");
                alert.showAndWait();
            } else {
            MobilController.getInstance().add(new Mobil(
                    nevTF.getText(),
                    gyartoCombo.getValue(),
                    esimCB.isSelected()
            ));
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
