package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.ObjectInputFilter;
import java.net.URL;
import java.util.ResourceBundle;

public class UtazasFrissites implements Initializable {
    private UtazasController uc;

    @FXML private TextField nameInput;
    @FXML private TextArea descriptionInput;
    @FXML private ComboBox<String> destinationInput;
    @FXML private Spinner<Integer> peopleInput;
    @FXML private CheckBox serviceInput;
    @FXML private Spinner<Integer> daysInput;

    public static Utazas utazas;

    public UtazasFrissites() {
        ConfigManager cm = new ConfigManager(this.getClass());
        uc = UtazasController.getInstance(cm.getValue("dao"), cm.getValue("db.url"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        destinationInput.getItems().setAll(
                "Budapest", "Roma", "Mexico", "Mád"
        );

        if ( utazas != null ) {
            nameInput.setText(utazas.getNev());
            nameInput.setEditable(false);

            destinationInput.setValue(utazas.getUticel());
            descriptionInput.setText(utazas.getLeiras());
            peopleInput.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,2,utazas.getUtasok())
            );
            daysInput.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30,utazas.getEjszaka())
            );
            serviceInput.setSelected(utazas.getFelpanzio());
        } else {
            ((Stage)(destinationInput.getScene().getWindow())).close();
            System.err.println("HIba, user data null.");
        }
    }

    @FXML private void delete() {
        var utazas = new Utazas();
        utazas.setNev(nameInput.getText());
        if ( uc.delete(utazas) ) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Sikeres törlés");
            a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Sikertelen törlés");
            a.showAndWait();
        }
    }

    @FXML private void save(ActionEvent actionEvent) {
        StringBuilder errorBuilder = new StringBuilder();

        if (descriptionInput.getText().isEmpty()) {
            errorBuilder.append("Kerem adja meg a leirast\n");
        }

        if ( !errorBuilder.toString().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Form hiba");
            a.setHeaderText("Javítsd a következőket:");
            a.setContentText(errorBuilder.toString());
            return;
        }

        if (uc.update(new Utazas(
                nameInput.getText(),
                destinationInput.getValue(),
                serviceInput.isSelected(),
                peopleInput.getValue(),
                daysInput.getValue(),
                descriptionInput.getText()
        ))) {
            Stage stage = (Stage)nameInput.getScene().getWindow();
            stage.close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Nem sikerült menteni");
            a.setTitle("Mentés hiba");
            a.showAndWait();
        }
    }
}
