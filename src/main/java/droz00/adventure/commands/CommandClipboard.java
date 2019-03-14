package droz00.adventure.commands;

import droz00.adventure.Game;

/**
 * Command that shows files in clipboard (list of cut files)
 */
public class CommandClipboard extends Command {

    public CommandClipboard(Game game){
        super(game,"clipboard");
        description = "Show file(s) in clipboard";

    }

    /**
     * Checks if clipboard is empty or not. If not, writes down names of files.
     * StringBuilder is used
     * @param parameters no parameters
     * @return answer for the player
     */
    @Override
    public String executeCommand(String[] parameters) {
        if(parameters.length == 0){
            if(game.getClipboard().getFiles().size()==0){
                return "--no file(s) in clipboard--";
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String name:game.getClipboard().getFiles().keySet()){
                stringBuilder.append(" ");
                stringBuilder.append(name);

            }
            return stringBuilder.toString();
        }
        return getDescription();
    }

}
