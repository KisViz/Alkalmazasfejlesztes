package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
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
        table.getItems().setAll(
                uc.find(new Utazas())
        );
        table.refresh();
    }
}
