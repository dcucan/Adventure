package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.places.Place;

/**
 * Command for deleting neighbor's directories if they are empty
 */
public class CommandRmdir extends Command {

    public CommandRmdir(Game game) {
        super(game, "rmdir");
        description = "Removes directory if its empty";
    }

    /**
     * Deletes the neigbor's directory according to the name
     * @param parameters name of the directory
     * @return answer for the player
     */
    @Override
    public String executeCommand(String[] parameters) {
        if (parameters.length != 1) {
            return getDescription();
        }


        String neighborName = parameters[0];

        if (game.getCurrentPlace().hasNeighbor(neighborName) == false) {
            return "Directory not found";
        }

        Place neighbor = game.getCurrentPlace().getNeighbor(neighborName);

        if(neighbor.getNeighbors().size() > 1){
            return "Directory contains other directories. Cannot be deleted";
        }

        if (neighbor.getFiles().size() != 0) {
            return "Directory contains files. Cannot be deleted.";
        }

        game.getCurrentPlace().removeNeighbor(neighborName);
        neighbor.removeNeighbor(game.getCurrentPlace().getName());

        return "Directory successfully removed";
    }
}
