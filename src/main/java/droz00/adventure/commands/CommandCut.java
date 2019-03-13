package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.files.File;

/**
 * Cuts files from the directory if possible, stores them in clipboard
 */
public class CommandCut extends Command {


    public CommandCut(Game game) {
        super(game, "cut");
        description = "Cuts file(s)from the current directory, use command paste to paste it";
    }

    /**
     * Checks if parameters are really files in the current directory and can cut them if possible.
     * Uses StringBuilder
     * It is possible to cut more files if clipboard allows so
     * @param parameters name(s) of file(s) player wants to cut
     * @return answer for the player
     */
    @Override
    public String executeCommand(String[] parameters) {
        if (parameters.length == 0) {
            return getDescription();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String fileName : parameters) {
            if (game.getCurrentPlace().hasFile(fileName)) {
                if (game.getCurrentPlace().getFiles().get(fileName).getIsPossibleToCut() == false){
                    stringBuilder.append("\nYou dont have permission to cut this file");
                    // Skips the rest of the current iteration a checks the next files
                    continue;
                }
                // Checks if clipboard is not full
                if (game.getClipboard().hasFreeSpot()) {
                    File file = game.getCurrentPlace().removeFile(fileName);
                    game.getClipboard().addFile(file);
                    stringBuilder.append("\n");
                    stringBuilder.append("File " + file.getFileName() + " successfully cut");
                } else {
                    stringBuilder.append("\nClipboard is full");
                }
            } else {
                stringBuilder.append("\n");
                stringBuilder.append("File " + fileName + " not found");
            }
        }
        game.getCut().notifySubscribers();
        return stringBuilder.toString().substring(1);
    }

    @Override
    public String getDescription(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cuts files from the current directory.\n");
        stringBuilder.append("Takes 1 to n arguments which are names of files to cut.");
        return stringBuilder.toString();
    }

}
