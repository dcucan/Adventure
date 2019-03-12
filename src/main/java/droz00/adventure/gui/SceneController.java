package droz00.adventure.gui;


import droz00.adventure.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    private Game game;

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;

    @FXML
    private ListView<String> listView1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        textArea.setText(game.getWelcomeMessage());

        game.onRoomChange(() -> {
            System.out.println("room change");
        });

        game.onRoomChange(this::onRoomChange);

    }

    public void onRoomChange() {
        game.getCurrentPlace().getNeighbors().forEach((i, place) -> {
            System.out.println(place.getName());

            listView1.getItems().clear();
            listView1.getItems().add(place.getName());
        });
    }

    public void onButtonClick() {
        String input = textField.getText();

        String output = game.processCommand(input);


        textField.clear();

        textArea.appendText("\n");
        textArea.appendText(input + "\n");
        textArea.appendText(output);


    }

    public void onTextFieldAction() {
        onButtonClick();
    }

    public void onNewGame() {
        game = new Game();
        textArea.setText(game.getWelcomeMessage());
    }

    public void onClose() {
        Platform.exit();
    }

    public void onHelp() {
        String output = game.processCommand("help");
        textArea.setText(output);
    }


    public void clickOnDirectory(javafx.scene.input.MouseEvent mouseEvent) {


        if (mouseEvent.getClickCount() == 2) {
            String input = listView1.getSelectionModel().getSelectedItem();
            String output = game.processCommand("cd " + input);
            textArea.appendText("\n" + output);
        }

    }
}
