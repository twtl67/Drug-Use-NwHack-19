package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

//    todo

    @FXML
    TextField numMed;

    int num;

    public void start() {
        int num = Integer.parseInt(numMed.getText());
        for (int i = 0; i < num; i++) {

        }

    }


}
