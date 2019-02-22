package droz00.adventure;

import droz00.adventure.commands.Command;

import droz00.adventure.commands.CommandManager;
import droz00.adventure.files.Clipboard;
import droz00.adventure.places.Place;
import droz00.adventure.places.PlaceManager;

/**
 * Main class of the game, its an input and output of the game
 * All is set here
 */
public class Game {


    private boolean isOver;
    private boolean isWon;
    private CommandManager commandMap;
    private PlaceManager placeManager;
    private Place currentPlace;
    private Clipboard clipboard;

    public Game() {
        isOver = false;
        isWon = false;
        commandMap = new CommandManager(this);
        placeManager = new PlaceManager(this);
        clipboard = new Clipboard(this);

    }


    public String getWelcomeMessage() {
        return "Welcome to game, your current place is " + currentPlace.getName();
    }

    public String getGoodbyeMessage() {
        if (isWon == false) {
            return "You lost.";
        } else {
            return "You won";
        }
    }


    /**
     * executes command
     * <p>
     * commandName = key word of the command (the first word)
     * parameters = command following the key command (the rest)
     */
    public String processCommand(String command) {
        String[] words = command.split("[ \t]+");
        String commandName = words[0];
        String[] parameters = new String[words.length - 1];

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = words[i + 1];
        }


        if (commandMap.getCommandMap().containsKey(commandName)) {
            Command c = commandMap.getCommandMap().get(commandName);
            return c.executeCommand(parameters);
        } else {
            return "Unknown command";
        }


    }

    /**
     * getters
     *
     * @return
     */
    public boolean isOver() {
        return isOver;
    }

    public boolean isWon() {
        return isWon;
    }


    public void gameEnds(boolean isGameWon) {
        isOver = true;
        isWon = isGameWon;
    }

    public void setCurrentPlace(Place place) {
        currentPlace = place;
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    public CommandManager getCommandManager() {
        return commandMap;
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    public PlaceManager getPlaceManager() {
        return placeManager;
    }
}


