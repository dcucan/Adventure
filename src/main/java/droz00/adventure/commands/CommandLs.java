package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.places.Place;

import java.util.Map;

/**
 * Shows files in the current directory
 */
public class CommandLs extends Command {

    public CommandLs(Game game) {
        super(game, "ls");
    }

    /**
     * Gets neihbors and files of the current place
     *
     * @param parameters no parameters
     * @return answer to the player
     */
    @Override
    public String executeCommand(String[] parameters) {
        Map<String, Place> neighbors = game.getCurrentPlace().getNeighbors();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("directories:");


        for (String name : neighbors.keySet()) {
            stringBuilder.append(" ");
            stringBuilder.append(name);
        }

        stringBuilder.append("\n");
        if (game.getCurrentPlace().getFiles().size() == 0) {
            stringBuilder.append(" --no files were found--");
        } else {
            stringBuilder.append("files:");
            for (String name : game.getCurrentPlace().getFiles().keySet()) {
                stringBuilder.append(" ");
                stringBuilder.append(name);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String getDescription() {
        return "List of directories and files";
    }
}
