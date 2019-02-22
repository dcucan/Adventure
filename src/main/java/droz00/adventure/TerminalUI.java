package droz00.adventure;

import java.util.Scanner;

/**
 * terminal interface for playing the game
 */
public class TerminalUI {


    private Scanner scanner;


    public TerminalUI() {
        scanner = new Scanner(System.in);
    }


    public void play() {
        Game game = new Game();
        System.out.println(game.getWelcomeMessage());
        while (!game.isOver()) {

            System.out.print(game.getCurrentPlace().getName());
            System.out.print(":// ");

            String command = scanner.nextLine();

            String response = game.processCommand(command);

            System.out.println(response);

        }
        System.out.println(game.getGoodbyeMessage());
    }


}
