package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UjUtazas {

    public UjUtazas() {

        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(30);
        root.setPadding(new Insets(10, 10, 10, 10)); // paraméterek: fent, jobbra, lent, balra

        Text nevText = new Text("Nev");
        root.add(nevText, 0, 0);
        TextField nevTextField = new TextField();
        root.add(nevTextField, 1, 0);

        root.add(new Text("Uticel"), 0, 1);
        ObservableList<String> uticelok = FXCollections.observableArrayList("Roma", "Parizs", "London");
        ComboBox<String> uticelCombo = new ComboBox<String>();
        uticelCombo.setItems(uticelok);
        uticelCombo.setValue(uticelok.get(0));
        root.add(uticelCombo, 1, 1);

        CheckBox felpanzioCB = new CheckBox();
        root.addRow(2, new Text("Felpanzio"), felpanzioCB);

        Spinner<Integer> utasok = new Spinner<Integer>(1, 20, 4);
        root.addRow(3, new Text("Fo"), utasok);


        Spinner<Integer> napok = new Spinner<Integer>(1, 30, 7);
        napok.setEditable(true);
        root.addRow(4, new Text("Napok"), napok);

        TextArea leirasTA = new TextArea();
        leirasTA.setPrefRowCount(4);
        root.addRow(5, new Text("Leiras"), leirasTA);

        Button okB = new Button("OK");

        Button megseB = new Button("Megse");

        HBox gombok = new HBox();
        gombok.setAlignment(Pos.CENTER);
        gombok.setSpacing(20);
        gombok.getChildren().addAll(okB, megseB);

        root.add(gombok, // Node
                0,       // Sorindex
                6,       // Oszlop index
                2,       // Sortávolság
                1        // Oszloptávolság
        );

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Uj utazas");

        megseB.setOnAction(e -> {
            stage.close();
        });

        okB.setOnAction(e -> {
            if (nevTextField.getText().isEmpty()) {
                Utils.showError("Kerem adja meg az utazas nevet");
                return;
            }

            if (leirasTA.getText().isEmpty()) {
                Utils.showError("Kerem adja meg a leirast");
                return;
            }

            if (UtazasController.getInstance().add(new Utazas(
                    nevTextField.getText(),
                    uticelCombo.getValue(),
                    felpanzioCB.isSelected(),
                    utasok.getValue(),
                    napok.getValue(),
                    leirasTA.getText()
            ))) {
                stage.close();
            } else {
                Utils.showError("Nem sikerult menteni");
            }
        });
        stage.show();
    }
}
