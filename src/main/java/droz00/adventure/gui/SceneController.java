package droz00.adventure.gui;


import droz00.adventure.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Main controller for GUI
 */
public class SceneController {

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
    private ImageView fileShow;

    private ImageView mapImageView;

    private Stage mapStage;

    @FXML
    private Image imageF = new Image("/image/folder.png");
    private Image imageSafari = new Image("/image/safari.png");
    private Image imageFinder = new Image("/image/finder.png");
    private Image imageChrome = new Image("/image/chrome.png");
    private Image imageMacos = new Image("/image/macos.png");
    private Image imageWindows = new Image("/image/windows.png");
    private Image imageFile = new Image("/image/file.png");
    private Image imageMe = new Image("/image/me.png");
    private Image imagePrague = new Image("/image/prague.png");
    private Image imagepdf = new Image("/image/pdf.png");
    private Image imageXml = new Image("/image/xml.png");
    private Image imageScreenshot = new Image("/image/screenshot.png");
    private Image imageForSale = new Image("/image/forsale.png");
    private Image[] listOfImages = {imageF, imageSafari, imageFinder, imageChrome, imageMacos, imageWindows, imageFile};

    private MapGenerator mapGenerator;

    /**
     * Set up the first scene
     */
    public void initialize() {
        game = new Game();
        textArea.setText(game.getWelcomeMessage());
        mapGenerator = new MapGenerator(game);

        game.onRoomChange(this::onRoomChange);
        game.onAddNeighbor(this::onRoomChange);
        game.onRmDirNeighbor(this::onRoomChange);
        game.onCut(this::onRoomChange);
        game.onPaste(this::onRoomChange);
        game.onCurrentPlaceObs(this::onPlaceChange);
        label.setText("Files in " + game.getCurrentPlace().getName());
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());
        listView2.getItems().addAll(game.getCurrentPlace().getFiles().keySet());

        DirectoryImageManager directoryImageManager = new DirectoryImageManager();
        directoryImageManager.setDirectoryImage(listView1);

    }


    /**
     * Sets image of file to null
     */
    public void onPlaceChange() {
        label.setText("Files in " + game.getCurrentPlace().getName());
        fileShow.setImage(null);
    }

    /**
     * When place
     */
    public void onRoomChange() {
        listView1.getItems().clear();
        listView1.getItems().addAll(game.getCurrentPlace().getNeighbors().keySet());
        listView1.getItems().addAll(game.getCurrentPlace().getFiles().keySet());

        listView2.getItems().clear();
        listView2.getItems().addAll(game.getCurrentPlace().getFiles().keySet());
        fileShow.setImage(null);

        if (mapImageView != null) {
            Image map = mapGenerator.toImage();
            mapImageView.setImage(map);
        }

    }

    /**
     * Process command on button click
     */
    public void onButtonClick() {
        String input = textField.getText();

        String output = game.processCommand(input);


        textField.clear();

        textArea.appendText("\n");
        textArea.appendText(input + "\n");
        textArea.appendText(output);
        fileShow.setImage(null);


    }

    /**
     * Typing enter
     */
    public void onTextFieldAction() {
        onButtonClick();
    }


    /**
     * Click on new game menu item
     */
    public void onNewGame() {
        listView1.getItems().clear();
        listView2.getItems().clear();

        initialize();

        mapStage.close();
    }

    public void onClose() {
        Platform.exit();
    }

    /**
     * Click on help menu item
     */
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

    /**
     * When double click on file -> shows the picture of the file
     * @param mouseEvent
     */
    public void onFile(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {

            String input = listView2.getSelectionModel().getSelectedItem();


            if (input.contains("me.jpg")) {
                fileShow.setImage(imageMe);
            } else if (input.contains("forsale")) {
                fileShow.setImage(imageForSale);
            } else if (input.contains(".xml")) {
                fileShow.setImage(imageXml);
            } else if (input.contains("prague.jpg")) {
                fileShow.setImage(imagePrague);
            } else if (input.contains("pdf")) {
                fileShow.setImage(imagepdf);
            } else if (input.contains("screen")) {
                fileShow.setImage(imageScreenshot);

            } else if (input.length() > 1) {
                fileShow.setImage(imageFile);
            }

        }
    }

    /**
     * When click on directory -> go to that directory
     * @param mouseEvent
     */
    public void clickOnDirectory(javafx.scene.input.MouseEvent mouseEvent) {


        if (mouseEvent.getClickCount() == 2) {
            fileShow.setImage(null);
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


    /**
     * Click on cut menu item -> cuts the file
     */
    public void onCutFile() {
        String input = listView2.getSelectionModel().getSelectedItem();
        String output = game.processCommand("cut " + input);
        System.out.println(output);
        fileShow.setImage(null);
    }

    /**
     * Click on paste file meni item -> paste the file
     */
    public void onPasteFile() {

        listView2.getItems().addAll(game.getClipboard().getFiles().keySet());
        String item = game.processCommand("clipboard");
        game.processCommand("paste" + item);
        fileShow.setImage(null);

    }

    /**
     * Click on delete directory menu item -> deletes the selected directory
     */
    public void onDeleteDirectory() {
        String input = listView1.getSelectionModel().getSelectedItem();
        String output = game.processCommand("rmdir " + input);
        fileShow.setImage(null);
        if (!output.equals("Directory not found")) {
            textArea.appendText("\n" + output);

            if (!output.equals("Directory contains other directories. Cannot be deleted")) {
                textArea.appendText("\n" + output);
            }
        } else {
            textArea.appendText("\n" + input + " cannot be deleted");
        }

    }

    /**
     * Click on new directory menu item -> creates new directory
     */
    public void onNewDirectory() {
        game.processCommand("mkdir new_directory");
        fileShow.setImage(null);
    }

    /**
     * Click on show file -> shows files and directories
     */
    public void onShowFiles() {
        String output = game.processCommand("ls");
        textArea.appendText("\n" + output);
        fileShow.setImage(null);
    }

    /**
     * Click on show clipboard menu item -> shows files in clipboard
     */
    public void onShowClipboard() {
        String output = game.processCommand("clipboard");
        textArea.setText(output);
        fileShow.setImage(null);

    }

    /**
     * Goes back to the previous place
     */
    public void onUndo() {
        fileShow.setImage(null);
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

    /**
     * Checks the browser
     */
    public void onCheckBrowser() {
        fileShow.setImage(null);
        String output = game.processCommand("check");
        textArea.appendText("\n" + output);
    }

    /**
     * Shows the map in a new window
     */
    public void onShowMap() {
        mapStage = new Stage();
        Pane pane = new Pane();
        mapImageView = new ImageView();
        Image image = mapGenerator.toImage();
        mapImageView.setImage(image);
        pane.getChildren().add(mapImageView);
        Scene scene = new Scene(pane, 720, 540);
        mapStage.setScene(scene);
        mapStage.show();
    }


}
