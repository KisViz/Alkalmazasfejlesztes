package hu.alkfejl.utils;

import javafx.scene.control.Alert;

public class Utils {
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hiba");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
