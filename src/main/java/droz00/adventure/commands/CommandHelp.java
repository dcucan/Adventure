package droz00.adventure.commands;

import droz00.adventure.Game;

/**
 * If only help is typed, shows the description of the game
 * If help + command is typed, shows the description of the command
 */
public class CommandHelp extends Command {


    public CommandHelp(Game game) {
        super(game, "help");
    }

    @Override
    public String executeCommand(String[] parameters) {
        if (parameters.length == 0) {
            return getDescription();
        } else if (parameters.length == 1) {
            Command command = game.getCommandManager().getCommand(parameters[0]);
            if (command == null) {
                return "Unknown command";
            }
            return command.getDescription();
        }
        return "Unknown command";
    }

    @Override
    public String getDescription() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nYou have installed Windows to your computer, that unfortunately changed your internet browser to Safe Finder \n");
        stringBuilder.append("instead of Google, which is annoying. Try to change it back to Google using following commands: \n\n");
        for (String commandName : game.getCommandManager().getCommandMap().keySet()) {
            stringBuilder.append(" " + commandName + "\n");
        }
        stringBuilder.append("\nType help + name of command to see its description");
        return stringBuilder.toString();

    }

}