package org.example.view;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.controller.MobilController;
import org.example.model.Mobil;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {
    public TableView<Mobil> table;
    public TableColumn<Mobil, String> nev;
    public TableColumn<Mobil, String> gyarto;
    public TableColumn<Mobil, Boolean> esim;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setItems(FXCollections.observableArrayList( new MobilController().findAll()));

        nev.setCellValueFactory( new PropertyValueFactory<>( "nev" ));
        gyarto.setCellValueFactory( new PropertyValueFactory<>( "gyarto" ));
        esim.setCellValueFactory( new PropertyValueFactory<>( "esim" ));
    }
}
