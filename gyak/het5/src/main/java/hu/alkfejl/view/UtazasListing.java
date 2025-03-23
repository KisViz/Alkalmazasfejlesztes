package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class UtazasListing implements Initializable {
    @FXML private TableColumn<Utazas, String> destinationColumn;
    @FXML private TableColumn<Utazas, String> nameColumn;
    UtazasController uc = UtazasController.getInstance();
    @FXML private TextField searchField;
    @FXML private TableView<Utazas> table;

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
        nameColumn.setCellValueFactory( new PropertyValueFactory<>("nev") );
        destinationColumn.setCellValueFactory( new PropertyValueFactory<>("uticel") );
        table.getItems().setAll(
                uc.find(new Utazas())
        );
        table.refresh();
    }
}
