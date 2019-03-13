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
    private Observable roomChanges;
    private Observable mkDirNeighbor;
    private Observable rmDirNeighbor;


    public Game() {
        roomChanges = new Observable();
        mkDirNeighbor = new Observable();
        rmDirNeighbor = new Observable();
        isOver = false;
        isWon = false;
        commandMap = new CommandManager(this);
        placeManager = new PlaceManager(this);
        clipboard = new Clipboard(this);
    }

    public void onAddNeighbor(Runnable callback){
        mkDirNeighbor.subscribe(callback);
    }

    public void onRoomChange(Runnable callback) {

        roomChanges.subscribe(callback);
    }

    public void onRmDirNeighbor(Runnable callback){
        rmDirNeighbor.subscribe(callback);
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

    public Observable getMkDirNeighbor(){ return mkDirNeighbor;}

    public Observable getRoomChanges(){return roomChanges;}

    public Observable getRmDirNeighbor(){return rmDirNeighbor;}


}


