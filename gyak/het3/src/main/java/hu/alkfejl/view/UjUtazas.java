package hu.alkfejl.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UjUtazas {
    public UjUtazas() {
        Stage ujAblak = new Stage();
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 640, 500);

        int sorId = 0;

        root.add(new Text("Utazás neve:"), 0, sorId);
        TextField utazasNeve = new TextField("hint: név");
        root.add(utazasNeve, 1, sorId);
        sorId++;

        root.add(new Text("Úticél:"), 0, sorId);
        ObservableList<String> varosok = FXCollections.observableArrayList(
                "Felcsút", "Alcsút", "Középcsút");
        ComboBox<String> helyek = new ComboBox<String>();
        helyek.setItems(varosok); //értékek beállítása
        helyek.setValue("Válassz!"); //default érték
        root.add(helyek, 1, sorId);
        sorId++;

        root.add(new Text("Félpanzió?"), 0, sorId);
        CheckBox felpanzio = new CheckBox();
        root.add(felpanzio, 1, sorId);
        sorId++;

        root.add(new Text("Vendégek száma:"), 0, sorId);
        Spinner<Integer> vendegekSzama = new Spinner<Integer>(1, 12, 1);
        vendegekSzama.setEditable(true);
        root.add(vendegekSzama, 1, sorId);
        sorId++;

        root.add(new Text("Éjszakák száma:"), 0, sorId);
        Spinner<Integer> ejszakakSzama = new Spinner<Integer>(2, 30, 1);
        vendegekSzama.setEditable(true);
        root.add(ejszakakSzama, 1, sorId);
        sorId++;

        Button megse = new Button("Mégse");
        megse.setOnAction(a -> {
            ujAblak.close();
        });
        root.add(megse, 0, sorId);
        Button mentes = new Button("Mentés");
        mentes.setOnAction(a -> {
            StringBuilder hibak = new StringBuilder();
            if (utazasNeve.getText().isBlank()) {
                hibak.append("Adj meg egy nevet!");
            }
            //...
            if (hibak.toString().isEmpty()) {
                //mentes
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(hibak.toString());
                alert.setHeaderText("Hibák");
                alert.showAndWait();
            }

        });
        root.add(mentes, 1, sorId);

        ujAblak.setScene(scene);
        ujAblak.show();
    }
}