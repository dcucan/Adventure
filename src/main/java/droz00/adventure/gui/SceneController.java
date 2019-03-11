package droz00.adventure.gui;


import com.sun.tools.javac.jvm.Items;
import droz00.adventure.Game;
import droz00.adventure.places.Place;
import javafx.beans.Observable;
import javafx.beans.value.ObservableMapValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    @FXML
    private TextField textField;

    public String onButtonClick() {
        String command = textField.getText();
        System.out.println(command);
        textField.clear();
        return command;
    }

    @FXML
    private ListView<String> listView1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game game = new Game();
        ObservableList<String> data = FXCollections.observableArrayList(game.getCurrentPlace().getFiles().keySet());
        listView1.setItems(data) ;



    }
}
