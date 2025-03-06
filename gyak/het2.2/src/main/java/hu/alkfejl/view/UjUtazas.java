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
        Scene scene= new Scene(root, 640, 500);

        int sorIdx = 0;
        Text utazasNeve = new Text("Utazás neve: ");
        root.add(utazasNeve,0,sorIdx);
        TextField uN = new TextField("Írd be a nevet");
        root.add(uN,1,sorIdx);
        sorIdx++;

        Text Uticel = new Text("Úticél: ");
        root.add(Uticel,0,sorIdx);
        ObservableList<String> uc = FXCollections.observableArrayList(
                "Felcsut", "Alcsut", "Kozepcsut");
        ComboBox<String> combo = new ComboBox<String>();
        combo.setItems(uc);
        combo.setValue("Válassz!");
        root.add(combo, 1, sorIdx);
        sorIdx++;

        Text felPanzio = new Text("Félpanzió?");
        root.add(felPanzio,0,sorIdx);
        CheckBox fP = new CheckBox();
        root.add(fP, 1, sorIdx);
        sorIdx++;

        Text vendegSzam = new Text("Vendégek száma:");
        root.add(vendegSzam,0,sorIdx);
        Spinner<Integer> vSz = new Spinner<>(1,12,1);
        vSz.setEditable(true);
        root.add(vSz, 1, sorIdx);
        sorIdx++;

        root.add(new Text("Éjszakák száma: "),0, sorIdx);
        Spinner<Integer> eSz = new Spinner<>(2, 30 ,2);
        eSz.setEditable(true);
        root.add(eSz, 1, sorIdx);
        sorIdx++;

        Button megse = new Button("Mégse");
        megse.setOnAction(e->{
            ujAblak.close();
        });
        root.add(megse,1,sorIdx);
        sorIdx++;

        Button mentes = new Button("Mentés");
        mentes.setOnAction(
                e -> {
                    StringBuilder hibak = new StringBuilder();
                    if (uN.getText().isBlank()) {
                        hibak.append("Adj meg egy nevet!");
                    }

                    //tobbi

                    if (hibak.toString().isEmpty()) {
                        //mentes
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(hibak.toString());
                        alert.setHeaderText("Hibák");
                        alert.showAndWait();
                    }
                }
        );
        root.add(mentes, 0, sorIdx);
        sorIdx++;

        ujAblak.setScene(scene);
        ujAblak.show();
    }

}
