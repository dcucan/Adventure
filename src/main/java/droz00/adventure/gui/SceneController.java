package droz00.adventure.gui;


import droz00.adventure.Game;
import droz00.adventure.places.Place;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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


    @FXML
    private Image imageF = new Image("/image/folder.png");
    private Image imageSafari = new Image("/image/safari.png");
    private Image imageFinder = new Image("/image/finder.png");
    private Image imageChrome = new Image("/image/chrome.png");
    private Image imageMacos = new Image("/image/macos.png");
    private Image imageWindows = new Image("/image/windows.png");
    private Image[] listOfImages = {imageF, imageSafari, imageFinder, imageChrome, imageMacos, imageWindows};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        textArea.setText(game.getWelcomeMessage());

        game.onRoomChange(() -> {
            System.out.println("room change");
        });
        game.onRoomChange(this::onRoomChange);
        game.onAddNeighbor(this::onRoomChange);
        game.onRmDirNeighbor(this::onRoomChange);
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());

        setImage();

    }

    public void setImage() {
        listView1.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (name.equals("safari"))
                        imageView.setImage(listOfImages[1]);
                    else if (name.equals("chrome"))
                        imageView.setImage(listOfImages[3]);
                    else if (name.equals("finder"))
                        imageView.setImage(listOfImages[2]);
                    else if (name.equals("macos"))
                        imageView.setImage(listOfImages[4]);
                    else if (name.equals("windows"))
                        imageView.setImage(listOfImages[5]);
                    else if (name.length() > 1)
                        imageView.setImage(listOfImages[0]);
                    setText(name);
                    setGraphic(imageView);
                }

            }
        });

    }

    public void onRoomChange() {
        listView1.getItems().clear();
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());
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
        listView1.getItems().clear();
        game.onRoomChange(() -> {
            System.out.println("room change");
        });
        game.onRoomChange(this::onRoomChange);
        game.onAddNeighbor(this::onRoomChange);
        game.onRmDirNeighbor(this::onRoomChange);
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());

        setImage();
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

    public void onNewDirectory(){
        game.processCommand("mkdir new_directory");
    }

    public void onShowFiles(){
        String output = game.processCommand("ls");
        textArea.setText(output);
    }
}
