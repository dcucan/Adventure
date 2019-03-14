package droz00.adventure.gui;


import droz00.adventure.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
    private Label label;

    @FXML
    private ListView<String> listView2;

    @FXML
    private WebView html;


    @FXML
    private Image imageF = new Image("/image/folder.png");
    private Image imageSafari = new Image("/image/safari.png");
    private Image imageFinder = new Image("/image/finder.png");
    private Image imageChrome = new Image("/image/chrome.png");
    private Image imageMacos = new Image("/image/macos.png");
    private Image imageWindows = new Image("/image/windows.png");
    private Image imageFile = new Image("/image/file.png");
    private Image[] listOfImages = {imageF, imageSafari, imageFinder, imageChrome, imageMacos, imageWindows, imageFile};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        textArea.setText(game.getWelcomeMessage());

        game.onRoomChange(this::onRoomChange);
        game.onAddNeighbor(this::onRoomChange);
        game.onRmDirNeighbor(this::onRoomChange);
        game.onCut(this::onRoomChange);
        game.onPaste(this::onRoomChange);
        game.onCurrentPlaceObs(this::onPlaceChange);
        label.setText("Files in " + game.getCurrentPlace().getName());
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());
        listView2.getItems().addAll(game.getCurrentPlace().getFiles().keySet());

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
                    else if (name.contains(".jpg"))
                        imageView.setImage(listOfImages[6]);
                    else if (name.length() > 1)
                        imageView.setImage(listOfImages[0]);

                    setText(name);
                    setGraphic(imageView);
                }

            }
        });

    }

    public void onPlaceChange() {
        label.setText("Files in " + game.getCurrentPlace().getName());
    }

    public void onRoomChange() {
        listView1.getItems().clear();
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());
        listView1.getItems().addAll(game.getCurrentPlace().getFiles().keySet());

        listView2.getItems().clear();
        listView2.getItems().addAll(game.getCurrentPlace().getFiles().keySet());
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

        game.onRoomChange(this::onRoomChange);
        game.onAddNeighbor(this::onRoomChange);
        game.onRmDirNeighbor(this::onRoomChange);
        game.onCut(this::onRoomChange);
        game.onPaste(this::onRoomChange);
        game.onCurrentPlaceObs(this::onPlaceChange);
        label.setText("Files in " + game.getCurrentPlace().getName());
        listView1.getItems().clear();
        listView2.getItems().clear();
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());
        listView2.getItems().addAll(game.getCurrentPlace().getFiles().keySet());

        setImage();
    }

    public void onClose() {
        Platform.exit();
    }

    public void onHelp() {

        StringBuilder sb = new StringBuilder();

        try {
            Stage stage = new Stage();
            stage.setTitle("About");
            Scene scene = new Scene(new Group());

            VBox root = new VBox();
            html = new WebView();
            WebEngine webEngine = html.getEngine();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(html);
            webEngine.loadContent("\n" +
                    "<h1 style=\"text-align: center;\">MacHack</h1>\n" +
                    "<blockquote>\n" +
                    "    <p style=\"text-align: justify;\">You have installed Windows to your computer, " +
                    "that unfortunately changed your internet browser to Safe Finder instead of Google, " +
                    "which is annoying. Try to change it back to Google using following commands:</p>\n" +
                    "</blockquote>\n" +
                    "<ul>\n" +
                    "    <li>cd</li>\n" +
                    "    <li>exit</li>\n" +
                    "    <li>help</li>\n" +
                    "    <li>cut</li>\n" +
                    "    <li>ls</li>\n" +
                    "    <li>paste</li>\n" +
                    "    <li>check</li>\n" +
                    "    <li>rmdir</li>\n" +
                    "    <li>mkdir</li>\n" +
                    "    <li>clipboard</li>\n" +
                    "</ul>\n" +
                    "<p style=\"text-align: center;\">Type help + name of command to see its description</p>");

            root.getChildren().addAll(scrollPane);
            scene.setRoot(root);

            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load the page");
        }

    }


    public void clickOnDirectory(javafx.scene.input.MouseEvent mouseEvent) {


        if (mouseEvent.getClickCount() == 2) {
            String input = listView1.getSelectionModel().getSelectedItem();
            String output = game.processCommand("cd " + input);
            textArea.appendText("\n" + output);
            if (input.equals("safari")) {

                try {
                    Stage stage = new Stage();
                    stage.setTitle("Safari");
                    WebView browser = new WebView();
                    WebEngine webEngine = browser.getEngine();
                    webEngine.load("https://www.google.com");
                    VBox root = new VBox();
                    root.getChildren().addAll(browser);

                    Scene scene = new Scene(root, 800, 500);
                    stage.setScene(scene);

                    stage.show();
                } catch (Exception e) {
                    System.out.println("exception");
                }
            }

            if (input.equals("chrome")) {

                try {
                    Stage stage = new Stage();
                    stage.setTitle("Google Chrome");
                    WebView browser = new WebView();
                    WebEngine webEngine = browser.getEngine();
                    webEngine.load("https://www.google.com");
                    VBox root = new VBox();
                    root.getChildren().addAll(browser);

                    Scene scene = new Scene(root, 800, 500);
                    stage.setScene(scene);

                    stage.show();
                } catch (Exception e) {
                    System.out.println("No internet connection");
                }
            }
        }


    }


    public void onCutFile() {
        String input = listView2.getSelectionModel().getSelectedItem();
        String output = game.processCommand("cut " + input);
        System.out.println(output);
    }

    public void onPasteFile() {

        listView2.getItems().addAll(game.getClipboard().getFiles().keySet());
        String item = game.processCommand("clipboard");
        game.processCommand("paste" + item);

    }

    public void onDeleteDirectory() {
        String input = listView1.getSelectionModel().getSelectedItem();
        String output = game.processCommand("rmdir " + input);
        if (!output.equals("Directory not found")) {
            textArea.appendText("\n" + output );

        if(!output.equals("Directory contains other directories. Cannot be deleted")){
            textArea.appendText("\n" + output );
        }
        } else {
            textArea.appendText("\n" + input + " cannot be deleted");
        }
    }

    public void onNewDirectory() {
        game.processCommand("mkdir new_directory");
    }

    public void onShowFiles() {
        String output = game.processCommand("ls");
        textArea.appendText("\n" + output);
    }

    public void onShowClipboard() {
        String output = game.processCommand("clipboard");
        textArea.setText(output);

    }

    public void onUndo() {
        if (game.getPreviousPlaces().size() > 1) {
            String place = game.getPreviousPlace();
            game.processCommand("cd " + place);

            System.out.println(game.getPreviousPlaces());
            textArea.appendText("\nBack to " + place);
        } else {
            textArea.appendText("\nNo more undo");
            System.out.println(game.getPreviousPlaces());
        }

    }

    public void onCheckBrowser() {
        String output = game.processCommand("check");
        textArea.appendText("\n" + output);
    }


}
