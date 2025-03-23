package hu.alkfejl.view;

import hu.alkfejl.model.Utazas;
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
        Scene scene = new Scene(root,640,480);

        int sorId = 0;

        root.add(new Text("Utazás neve: "), 0, sorId);
        TextField utazasNeveTF = new TextField("Hint: add meg");
        root.add(utazasNeveTF, 1 , sorId);
        sorId++;

        root.add(new Text("Úticél: "), 0, sorId);
        ObservableList<String> varosok = FXCollections.observableArrayList(
                "Róma", "Párizs", "Szeged");

        ComboBox<String> uticelCombo = new ComboBox<String>();

        uticelCombo.setItems(varosok); //értékek beállítása
        uticelCombo.setValue("Válassz!"); //default érték
        root.add(uticelCombo, 1 , sorId);
        sorId++;

        root.add(new Text("Félpanzió: "), 0, sorId);
        CheckBox felpanzioCB = new CheckBox();
        root.add(felpanzioCB, 1 , sorId);
        sorId++;

        root.add(new Text("Vendégek száma: "), 0, sorId);
        Spinner<Integer> vendegszamSpinner = new Spinner<Integer>(1, 12, 1);
        vendegszamSpinner.setEditable(true);
        root.add(vendegszamSpinner, 1 , sorId);
        sorId++;

        root.add(new Text("Éjszakák száma: "), 0, sorId);
        Spinner<Integer> ejszakakszamaSpinner = new Spinner<Integer>(2, 30, 1);
        ejszakakszamaSpinner.setEditable(true);
        root.add(ejszakakszamaSpinner, 1 , sorId);
        sorId++;


        Button megse = new Button("Mégse");
        megse.setOnAction(a-> {
            ujAblak.close();
        });
        root.add(megse, 0, sorId);

        Button mentes = new Button("Mentes");
        mentes.setOnAction(a-> {
            StringBuilder hibak = new StringBuilder();
            if (utazasNeveTF.getText().isBlank()) {
                hibak.append("Add meg az utazás nevét!");
            }

            if (uticelCombo.equals(uticelCombo.getPlaceholder())) {
                hibak.append("Válassz uticélt!");
            }

            if (hibak.toString().isEmpty()) {
                Utazas uj = new Utazas(utazasNeveTF.getText(), uticelCombo.getValue(), felpanzioCB.isSelected(), vendegszamSpinner.getValue(), ejszakakszamaSpinner.getValue(), "");
                ujAblak.close();
            } else {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setContentText(hibak.toString());
                alert.setHeaderText("Baj van:(");
                alert.showAndWait();
            }

        });
        root.add(mentes, 1 ,sorId);
        sorId++;

        ujAblak.setScene(scene);
        ujAblak.show();
    }
}
