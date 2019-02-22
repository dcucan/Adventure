package droz00.adventure.commands;

import droz00.adventure.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Manage all the commands and saves them in the game
 */
public class CommandManager {

    private Map<String, Command> commandMap;

    private Game game;

    public CommandManager(Game game) {
        commandMap = new HashMap<>();
        this.game = game;
        setUp();
    }

    /**
     * Method to add command to the command map
     * @param command
     */
    private void addCommand(Command command) {
        commandMap.put(command.getName(), command);

    }

    /**
     * Adds commands to the game
     */
    private void setUp() {
        addCommand(new CommandLs(game));
        addCommand(new CommandCd(game));
        addCommand(new CommandExit(game));
        addCommand(new CommandHelp(game));
        addCommand(new CommandCut(game));
        addCommand(new CommandPaste(game));
        addCommand(new CommandClipboard(game));
        addCommand(new CommandCheck(game));
        addCommand(new CommandMkdir(game));
        addCommand(new CommandRmdir(game));
    }

    /**
     *
     * @param name
     * @return command found by the name
     */
    public Command getCommand(String name) {
        return commandMap.get(name);
    }

    /**
     * returns map of the commands
     * @return
     */
    public Map<String, Command> getCommandMap() {
        return commandMap;
    }


}
