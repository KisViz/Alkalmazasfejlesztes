package org.example.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.controller.MobilController;
import org.example.model.Mobil;

import java.net.URL;
import java.util.ResourceBundle;

public class Felvitel implements Initializable {
    public TextField nev;
    public ComboBox<String> gyarto;
    public CheckBox esim;

    public void cancel() {
        ((Stage)nev.getScene().getWindow()).close();
    }

    public void save(ActionEvent actionEvent) {

        if ( nev.getText().isBlank() ) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Felvitel hiba");
            a.setContentText("Nincs nev");
            a.showAndWait();
            return;
        }

        var mobil = new Mobil();
        mobil.setNev( nev.getText() );
        mobil.setGyarto( gyarto.getValue() );
        mobil.setEsim( esim.isSelected() );
        if ( new MobilController().save( mobil ) ) {
            cancel();
            return;
        }

        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Felvitel hiba");
        a.setContentText("Sikertelen felvitel. ASK SOMEONE");
        a.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gyarto.setItems(
                FXCollections.observableArrayList( "Samsung", "Apple" )

        );
        gyarto.setValue( gyarto.getItems().get(0) );
    }
}
