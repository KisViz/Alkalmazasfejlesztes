package hu.alkfejl.view;

import hu.alkfejl.App;
import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UtazasListing implements Initializable {
    @FXML private TableColumn<Utazas, String> destinationColumn;
    @FXML private TableColumn<Utazas, String> nameColumn;

    @FXML private TextField searchField;
    @FXML private TableView<Utazas> table;

    private UtazasController uc;

    @FXML
    private void filter(ActionEvent actionEvent) {
        String uticel = searchField.getText();
        if ( uticel == null || uticel.isBlank() ) {
            uticel = null;
        }
        var filterUtazas = new Utazas();
        filterUtazas.setUticel( uticel );
        table.setItems(
                FXCollections.observableList(
                        uc.find(filterUtazas)
                )
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConfigManager cm = new ConfigManager(this.getClass());
        uc = UtazasController.getInstance(cm.getValue("dao"), cm.getValue("db.url"));
        nameColumn.setCellValueFactory( new PropertyValueFactory<>("nev") );
        destinationColumn.setCellValueFactory( new PropertyValueFactory<>("uticel") );
        table.setRowFactory( utazasTableView -> {
            TableRow<Utazas> row = new TableRow<>();
            row.setOnMouseClicked( event -> {
                if ( !row.isEmpty() ) {
                    Utazas u = row.getItem();
                    Stage stage = new Stage();
                    Scene scene = null;
                    UtazasFrissites.utazas = u;
                    try {
                        scene = new Scene(App.loadFXML("utazasFrissites"));
                    } catch (IOException e) {
                        System.err.println("Hiba: " + e.getMessage());
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        e.printStackTrace();
                        a.setContentText("Hiba a form betöltésekor.");
                        a.showAndWait();
                    }
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return row;
        });
        table.getItems().setAll(
                uc.find(new Utazas())
        );
        table.refresh();
    }
}
