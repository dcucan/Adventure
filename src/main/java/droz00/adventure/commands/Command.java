package droz00.adventure.commands;

import droz00.adventure.Game;

/**
 * Abstrakní třída pro všechny příkazy
 */
public abstract class Command {

    protected String name;
    protected String description;
    protected Game game;

    public Command(Game game, String name) {
        this.game = game;
        this.name = name;
        description = "";
    }

    public abstract String executeCommand(String[] parameters);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Protože příkazy mají unikátní jméno, generaci příkazů můžu nechat na názvu příkazu
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
