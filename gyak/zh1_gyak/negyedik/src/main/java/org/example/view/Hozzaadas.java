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
        Scene scene = new Scene(root,640,480);

        int index = 0;

        root.addColumn(0, new Text("Név"), new Text("Gyártó"), new Text("eSim"));

        TextField nevTF = new TextField();

        ObservableList<String> gyartok = FXCollections.observableArrayList(
                "Apple", "Samsung", "Nokia", "Xiaomi", "Huawei");

        ComboBox<String> gyartokCombo = new ComboBox<String>();

        gyartokCombo.setItems(gyartok); //értékek beállítása

        CheckBox esimCB = new CheckBox();

        root.addColumn(1, nevTF, gyartokCombo, esimCB);


        Button ok = new Button("OK");
        Button megse = new Button("Megse");

        megse.setOnAction(a-> {
            stage.close();
        });

        ok.setOnAction(a-> {
            StringBuilder hibak = new StringBuilder();
            if (nevTF.getText().isBlank()) {
                hibak.append("Add meg a nevet!\n");
            }
            if (gyartokCombo.getValue() == null) {
                hibak.append("Add meg a gyártót!");
            }

            if (hibak.toString().isBlank()) {
                MobilController.getInstance().add(new Mobil(
                        nevTF.getText(),
                        gyartokCombo.getValue(),
                        esimCB.isSelected()
                ));
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Kacsa");
                alert.setContentText(hibak.toString());
                alert.showAndWait();
            }
        });


        root.add(ok, 0, 3);
        root.add(megse, 1, 3);

        stage.setScene(scene);
        stage.show();
    }
}
