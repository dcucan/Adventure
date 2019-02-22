package droz00.adventure.commands;

import droz00.adventure.Game;

/**
 * Exits the game
 */
public class CommandExit extends Command {


    public CommandExit(Game game) {
        super(game, "exit");
        description = "Exit game";
    }


    @Override
    public String executeCommand(String[] parameters) {
        game.gameEnds(false);
        return "Game over";
    }

}
