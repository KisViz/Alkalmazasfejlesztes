package hu.alkfejl.common;

import hu.alkfejl.App;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public MenuItem utazasFelvitelMI;
    public MenuItem utazasListazasMI;
    public MenuItem latnivaloFelvitelMI;
    public MenuItem latnivaloListazasMI;

    public void openUtazasFelvitel(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(App.loadFXML("utazasFelvitel"));
        } catch (IOException e) {
            System.err.println("Hiba: " + e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Hiba a form betöltésekor.");
            a.showAndWait();
        }
        stage.setScene(scene);
        stage.show();
    }

    public void setUtazasListing(ActionEvent actionEvent) {
        try {
            App.setRoot("utazasListing");
        } catch(IOException e) {
            System.err.println("Nem lehetett beolvasni az utazasListing-et." + e.getMessage());
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nem lehetett beolvasni a leirot." + e.getMessage());
            alert.showAndWait();
        }
    }

    public void setUtazasPreferences(ActionEvent actionEvent) {
        try {
            App.setRoot("utazasPreferences");
        } catch(IOException e) {
            System.err.println("Nem lehetett beolvasni az utazasListing-et." + e.getMessage());
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nem lehetett beolvasni a leirot." + e.getMessage());
            alert.showAndWait();
        }
    }

    public void openLatnivaloFelvitel(ActionEvent actionEvent) {
    }

    public void setLatnivaloListing(ActionEvent actionEvent) {
    }


}
