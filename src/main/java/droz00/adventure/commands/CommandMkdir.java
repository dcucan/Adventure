package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.places.Place;

/**
 * Makes new directory
 */
public class CommandMkdir extends Command {

    public static final String SUCCESS_MESSAGE = "New directory created.";

    public static final String ALREADY_EXISTS_MESSAGE = "This directory already exists.";

    public CommandMkdir(Game game) {
        super(game, "mkdir");
        description = "Make new directory";
    }

    /**
     * Checks if the directory doesnt exist, then its possible to create a new one
     *
     * @param parameters name of the new directory
     * @return
     */
    @Override
    public String executeCommand(String[] parameters) {
        if (parameters.length != 1) {
            return getDescription();
        }

        String neighborName = parameters[0];

        if (game.getCurrentPlace().hasNeighbor(neighborName)) {
            return ALREADY_EXISTS_MESSAGE;
        }

        String neighborDescription = "Simple directory";
        Place place = new Place(neighborName, neighborDescription);

        // register new place
        game.getPlaceManager().registerPlace(place);
        game.getPlaceManager().setNeighbors(place, game.getCurrentPlace());
        game.getMkDirNeighbor().notifySubscribers();
        return SUCCESS_MESSAGE;
    }
}
