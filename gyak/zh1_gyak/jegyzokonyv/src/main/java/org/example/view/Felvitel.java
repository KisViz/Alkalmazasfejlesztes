package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.JegyzokonyvController;
import org.example.model.Jegyzokonyv;

public class Felvitel {
    public Felvitel() {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 640,480);
        Stage stage = new Stage();

        int index = 0;
        root.add(new Text("Cím"), 0 ,index);
        TextField cimTF = new TextField();
        root.add(cimTF, 1, index);
        index++;

        root.add(new Text("Leírás"), 0 ,index);
        TextField leirasTF = new TextField();
        root.add(leirasTF, 1, index);
        index++;

        root.add(new Text("Dátum"), 0 ,index);
        TextField datumTF = new TextField();
        root.add(datumTF, 1, index);
        index++;

        root.add(new Text("Jegyző"), 0 ,index);
        TextField jegyzoTF = new TextField();
        root.add(jegyzoTF, 1, index);
        index++;

        Button mentes = new Button("Mentés");
        root.add(mentes, 0, index);
        index++;

        mentes.setOnAction(a-> {
            JegyzokonyvController.getInstance().add(new Jegyzokonyv(
                    cimTF.getText(),
                    leirasTF.getText(),
                    datumTF.getText(),
                    jegyzoTF.getText()
            ));
        });

        stage.setScene(scene);
        stage.show();
    }
}
