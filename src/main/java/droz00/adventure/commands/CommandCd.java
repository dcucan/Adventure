package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.places.Place;

/**
 * Command for changing directories
 * Accepts 1 parameter
 */
public class CommandCd extends Command {


    public CommandCd(Game game) {
        super(game, "cd");
        description = "Change directory, use cd + name of directory where you want to go";
    }

    /**
     * Exectues command:
     * 1. checks parameters lenght, if 0 -> returns description
     *                              if 1 -> checks if place exists
     * 2. changes to place
     * @param parameters name of the directory where you want to go
     * @return Answer
     */
    @Override
    public String executeCommand(String[] parameters) {
        if (parameters.length == 0) {
            return getDescription();
        } else if (parameters.length == 1) {
            Place place = game.getCurrentPlace().getNeighbor(parameters[0]);
            if (place == null) {
                return "Unknown directory";
            }
            game.setCurrentPlace(place);
            game.getRoomChanges().notifySubscribers();
            return "Changed to " + place.getName();
        }

        return "Type help + cd";

    }

}
