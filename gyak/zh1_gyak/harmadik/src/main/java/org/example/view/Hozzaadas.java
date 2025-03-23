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

import java.util.Objects;

public class Hozzaadas {
    public Hozzaadas() {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 640, 480);
        Stage stage = new Stage();

        int index = 0;
        root.add(new Text("Nev"), 0, index);
        TextField nevTF = new TextField();
        root.add(nevTF, 1, index);
        index++;

        root.add(new Text("Gyarto"), 0, index);
        ObservableList<String> gyartok = FXCollections.observableArrayList(
                "Apple", "Samsung", "Nokia", "Xiaomi", "Huawei"
        );

        ComboBox<String> gyartoCB = new ComboBox<String>();

        gyartoCB.setItems(gyartok); //értékek beállítása
        gyartoCB.setValue(""); //default érték
        root.add(gyartoCB, 1, index);
        index++;

        root.add(new Text("eSim"), 0, index);
        CheckBox esimCB = new CheckBox();
        root.add(esimCB, 1, index);
        index++;

        Button ok = new Button("OK");
        root.add(ok, 0, index);
        Button megse = new Button("Megse");
        root.add(megse, 1, index);

        megse.setOnAction(a-> {
            stage.close();
        });

        ok.setOnAction(a-> {
            StringBuilder hibak = new StringBuilder();

            if (nevTF.getText().isBlank()) {
                hibak.append("Add meg a nevet!\n");
            }
            if (Objects.equals(gyartoCB.getValue(), "")) {
                hibak.append("Válassz egy gyártót!\n");
            }

            if (hibak.toString().isBlank()) {
                MobilController.getInstance().add(new Mobil(
                        nevTF.getText(),
                        gyartoCB.getValue(),
                        esimCB.isSelected()
                ));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Hiba");
                alert.setContentText(hibak.toString());
                alert.showAndWait();
            }
        });


        stage.setScene(scene);
        stage.show();
    }
}
