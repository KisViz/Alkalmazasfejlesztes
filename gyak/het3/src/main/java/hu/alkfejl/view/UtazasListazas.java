package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
//import hu.alkfejl.utils.Utils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UtazasListazas {
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public UtazasListazas(Stage stage) {
        Scene regi = stage.getScene();

        VBox root = new VBox();
        HBox kereso = new HBox();

        TextField filter = new TextField("Hint: úticél");
        Button kereses = new Button("Kereses");
        Button vissza = new Button("Vissza");
        vissza.setOnAction(a -> {
            stage.setScene(regi);
        });
        kereso.getChildren().addAll(new Text("Úticél"), filter, kereses, vissza);
        root.getChildren().addAll(kereso);

        TableView<Utazas> table = new TableView<>();

        TableColumn<Utazas, String> uticel = new TableColumn<>("Úticél");
        uticel.setCellValueFactory(new PropertyValueFactory<>("uticel"));

        TableColumn<Utazas, String> nev = new TableColumn<>("Név");
        nev.setCellValueFactory(new PropertyValueFactory<>("nev"));

        table.getColumns().addAll(uticel, nev);

        var utazasok = UtazasController.getInstance().find(new Utazas());
        table.getItems().addAll(utazasok);

        table.setRowFactory(r -> {
            TableRow<Utazas> sor = new TableRow<>();

            sor.setOnMouseClicked(a -> {
                var utazas = sor.getItem();
                if (utazas == null) {
                    return;
                }
                System.out.println(utazas.getLeiras());
            });

            return sor;
        });

        table.setOnMouseClicked(a -> {
            System.out.println("*table clicked*");
        });

        root.getChildren().add(table);

        kereses.setOnAction(a -> {
            String cel = filter.getText();
            if (cel.isBlank() && !cel.isEmpty()) {
                Alert hiba = new Alert(Alert.AlertType.ERROR);
                hiba.setHeaderText("Hiba");
                hiba.setContentText("Rendeset adj meg!");
                hiba.showAndWait();
            }

            var filteres = new Utazas();
            if (!cel.isEmpty()) {
                filteres.setUticel(cel);
            }
            var szur = UtazasController.getInstance().find(filteres);
            table.getItems().clear();
            table.getItems().addAll(szur);
        });

        scene = new Scene(root, 640, 500);
    }
}
