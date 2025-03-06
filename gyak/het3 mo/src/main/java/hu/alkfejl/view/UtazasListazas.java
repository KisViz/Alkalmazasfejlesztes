package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.Utils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UtazasListazas {

    private Scene scene;
    public Scene getScene() {
        return scene;
    }

    public UtazasListazas(Stage stage) {
        Scene old = stage.getScene();

        VBox root = new VBox();

        HBox searchGroup = new HBox();
        TextField filterInput = new TextField("hint:uticel");
        Button search = new Button("Kereses");


        searchGroup.getChildren().addAll(filterInput, search);
        root.getChildren().add(searchGroup);

        TableView<Utazas> table = new TableView<>();

        TableColumn<Utazas, String> uticelOsztlop = new TableColumn<>("Uticel");
        uticelOsztlop.setCellValueFactory(new PropertyValueFactory<>("uticel"));

        TableColumn<Utazas, String> utazasNeve = new TableColumn<>("Neve");
        utazasNeve.setCellValueFactory(new PropertyValueFactory<>("nev"));

        table.getColumns().addAll(uticelOsztlop, utazasNeve);

        var utazasok = UtazasController.getInstance().find(new Utazas());
        table.getItems().addAll(utazasok);

        table.setRowFactory(r-> {
            TableRow<Utazas> row = new TableRow<>();

            row.setOnMouseClicked( e-> {
                var utazas = row.getItem();
                if (utazas == null) {
                    return;
                }
                System.out.println(utazas.getLeiras());
            });

            return row;
        });
        table.setOnMouseClicked(e-> {
            System.out.println("table clicked");
        });


        root.getChildren().add(table);


        search.setOnAction(
                e-> {
//                    stage.setScene(old); //vissza gomb
                    //todo

                    String uticel = filterInput.getText();
                    if (uticel.isBlank() && !uticel.isEmpty()) {
                        Utils.showError("Rendeset ajd meg!");
                        return;
                    }

                    var filter = new Utazas();
                    if (!uticel.isEmpty()) {
                        filter.setUticel(uticel);
                    }
                    var szurtLista = UtazasController.getInstance().find(filter);
                    table.getItems().clear();
                    table.getItems().addAll(szurtLista);
                }
        );


        scene = new Scene(root, 640, 500);

    }
}
