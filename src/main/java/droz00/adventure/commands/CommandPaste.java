package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.files.File;

/**
 * Paste file(s) from the clipboard to the current directory
 */
public class CommandPaste extends Command {


    public CommandPaste(Game game) {
        super(game, "paste");
    }

    /**
     *
     * @param parameters name(s) of the file(s)
     * @return answer to the player
     */
    @Override
    public String executeCommand(String[] parameters) {
        if (parameters.length == 0) {
            return getDescription();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String fileName : parameters) {
            if (game.getClipboard().hasFile(fileName)) {

                File file = game.getClipboard().removeFile(fileName);
                game.getCurrentPlace().addFile(file);
                 stringBuilder.append("\nFile " + fileName + " successfully pasted");
            } else {
                stringBuilder.append("\nFile " + fileName + " not found");
            }

        }
        return stringBuilder.toString().substring(1);
    }

    @Override
    public String getDescription(){
        return "Paste file(s) to current directory";
    }
}
