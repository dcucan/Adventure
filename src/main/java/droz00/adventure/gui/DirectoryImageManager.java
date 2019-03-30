package droz00.adventure.gui;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DirectoryImageManager {

    private ListView<String> listView;

    private Image imageF = new Image("/image/folder.png");
    private Image imageSafari = new Image("/image/safari.png");
    private Image imageFinder = new Image("/image/finder.png");
    private Image imageChrome = new Image("/image/chrome.png");
    private Image imageMacos = new Image("/image/macos.png");
    private Image imageWindows = new Image("/image/windows.png");
    private Image imageFile = new Image("/image/file.png");

    private Image[] listOfImages = {imageF, imageSafari, imageFinder, imageChrome, imageMacos, imageWindows, imageFile};



    public void setDirectoryImage(ListView<String> listView) {
        listView.setCellFactory(param -> new ListCell<String>() {
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

}
