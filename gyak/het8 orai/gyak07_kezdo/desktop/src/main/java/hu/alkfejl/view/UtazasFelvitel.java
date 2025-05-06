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

public class UtazasFelvitel implements Initializable {
    private UtazasController uc;

    @FXML private TextField nameInput;
    @FXML private TextArea descriptionInput;
    @FXML private ComboBox<String> destinationInput;
    @FXML private Spinner<Integer> peopleInput;
    @FXML private CheckBox serviceInput;
    @FXML private Spinner<Integer> daysInput;

    public UtazasFelvitel() {
        ConfigManager cm = new ConfigManager(this.getClass());
        uc = UtazasController.getInstance(cm.getValue("dao"), cm.getValue("db.url"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        destinationInput.getItems().setAll(
                "Budapest", "Roma", "Mexico", "Mád"
        );
        peopleInput.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12,2,1)
        );
        daysInput.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30,7)
        );
    }

    @FXML private void save(ActionEvent actionEvent) {
        StringBuilder errorBuilder = new StringBuilder();
        if (nameInput.getText().isEmpty()) {
            errorBuilder.append("Kerem adja meg az utazas nevet\n");
        }

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

        if (uc.add(new Utazas(
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
