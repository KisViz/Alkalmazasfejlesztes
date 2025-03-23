package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.JegyzokonyvController;
import org.example.model.Jegyzokonyv;

public class Segitseg {
    public Segitseg() {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 640,480);
        Stage stage = new Stage();

        root.add(new Text("Egy jegyzőkönyvről az alábbiak ismertek: - cím: Rövid téma a jegyzőkönyvről - részletes leírás: Jegyzőkönyv részletes leírása - dátum: Felvitel időpontja - jegyző: Ki vitte fel"), 0, 0);

        stage.setScene(scene);
        stage.show();
    }
}
