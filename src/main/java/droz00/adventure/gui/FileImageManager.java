package droz00.adventure.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

public class FileImageManager {

    private Image imageFile = new Image("/image/file.png");
    private Image imageMe = new Image("/image/me.png");
    private Image imagePrague = new Image("/image/prague.png");
    private Image imagepdf = new Image("/image/pdf.png");
    private Image imageXml = new Image("/image/xml.png");
    private Image imageScreenshot = new Image("/image/screenshot.png");
    private Image imageForSale = new Image("/image/forsale.png");


    public void setFileImage(String input, ImageView fileShow){
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

        } else if(input.length()>1){fileShow.setImage(imageFile);}
    }


}
