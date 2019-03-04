package droz00.adventure.gui;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SceneController {

    @FXML
    private TextField textField;

    public void onButtonClick(){
        System.out.println("Klik");
        textField.setText("HelloWorld");
    }
}
