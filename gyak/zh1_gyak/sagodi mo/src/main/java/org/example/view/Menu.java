package org.example.view;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.App;

import java.io.IOException;

public class Menu {
    public void felvitel(ActionEvent actionEvent) {
        Stage stage = new Stage();

        Scene scene = null;
        try {
            scene = new Scene(App.loadFXML("felvitel"), 600, 450 );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene( scene );
        stage.show();
    }

    public void lista(ActionEvent actionEvent) {
        try {
            App.setRoot( "main" );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
