package droz00.adventure;

import com.sun.javafx.tk.TKScenePaintListener;
import droz00.adventure.gui.SceneController;

public class TerminalGUI {

    private SceneController sceneController;

    TerminalGUI(){
        sceneController = new SceneController();
    }



    public void play(){
        Game game = new Game();
        System.out.println(game.getWelcomeMessage());
        while (!game.isOver()) {

            System.out.print(game.getCurrentPlace().getName());
            System.out.print(":// ");

            String command = sceneController.onButtonClick();

            String response = game.processCommand(command);

            System.out.println(response);

        }
    }
}
