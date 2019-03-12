package droz00.adventure.gui;


import droz00.adventure.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    private Game game;

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        textArea.setText(game.getWelcomeMessage());
    }

    public void onButtonClick() {
        String input = textField.getText();

        String output = game.processCommand(input);

        textField.setText("");

        textArea.appendText("\n");
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
}
