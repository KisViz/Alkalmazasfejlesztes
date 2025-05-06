package hu.alkfejl.view;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class UtazasPreferences {

    UtazasController uc;

    public UtazasPreferences() {
        ConfigManager cm = new ConfigManager(this.getClass());
        uc = UtazasController.getInstance(cm.getValue("dao"), cm.getValue("db.url"));
    }

    @FXML private CheckBox serviceInput;

    @FXML private void save(ActionEvent actionEvent) {
        var utazas = new Utazas();
        utazas.setFelpanzio(serviceInput.isSelected());
        uc.preferences(utazas);
    }
}
