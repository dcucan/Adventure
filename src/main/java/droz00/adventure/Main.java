package droz00.adventure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {


        if (args.length > 0) {
            if (args[0].equals("text")) {
                TerminalUI terminal = new TerminalUI();
                terminal.play();

            } else {
                System.out.println("Invalid parameter, \n please type 'text' to start the user interface");
            }
        } else {

            launch(args);

        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scene.fxml"));
        Scene scene = new Scene(root, 720, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

