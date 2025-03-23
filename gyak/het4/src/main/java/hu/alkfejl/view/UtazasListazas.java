package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UtazasListazas {
     Scene scene;

    public Scene getScene() {
        return scene;
    }

    public UtazasListazas(Stage stage) {
         Scene regi = stage.getScene();

        GridPane root = new GridPane();


        TextField uticelTF = new TextField();
        root.add(uticelTF, 0 , 0);
        Button vissza = new Button("Vissza");
        root.add(vissza, 1 , 0);
        Button kereses = new Button("Keresés");
        root.add(kereses, 2 , 0);

        vissza.setOnAction(a-> {
            stage.setScene(regi);
        });

        TableView<Utazas> table = new TableView<>();
        TableColumn<Utazas, String> uticel = new TableColumn<>("Úticél");
        uticel.setCellValueFactory(new PropertyValueFactory<>("uticel"));

        TableColumn<Utazas, String> nev = new TableColumn<>("Név");
        nev.setCellValueFactory(new PropertyValueFactory<>("nev"));

        table.getColumns().addAll(uticel, nev);

        table.setRowFactory( tv -> {
            var sor = new TableRow<Utazas>();
            sor.setOnMouseClicked( click -> {
                var utazas = sor.getItem();
                if (utazas == null) {
                    return;
                }
                System.out.println(utazas.getLeiras());
            });
            return sor;
        });

        table.setOnMouseClicked(a-> {
            System.out.println("*tábla nyomkodva*");
        });

        root.add(table, 0, 1);

        kereses.setOnAction(a-> {
            String cel = uticelTF.getText();
            if (cel.isBlank() && !cel.isEmpty()) {
                Alert hiba = new Alert(Alert.AlertType.ERROR);
                hiba.setHeaderText("Hiba");
                hiba.setContentText("Rendeset adj meg!");
                hiba.showAndWait();
            }

            var uj = new Utazas();
            if (!cel.isEmpty()) {
                uj.setUticel(cel);
            }

            var szur = UtazasController.getInstance().find(uj);
            table.getItems().clear();
            table.getItems().addAll(szur);
        });






        scene = new Scene(root, 640, 480);





//        Stage stage = new Stage();

//        stage.setScene(utazasListazas);
//        stage.show();
    }
}
