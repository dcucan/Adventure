package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandRmdirTest {

    @Test
    public void deletesEmptyDirectory(){
        Game game = new Game();
        game.processCommand("mkdir new");
        assertEquals("Directory successfully removed", game.processCommand("rmdir new"));
    }

    @Test
    public void cannotDeleteDirectoryThatContainsOther(){
        Game game = new Game();
        assertEquals("Directory contains other directories. Cannot be deleted", game.processCommand("rmdir finder"));
    }

    @Test
    public void cannotDeleteDirectoryThatContainsFiles(){
        Game game = new Game();
        game.processCommand("cd finder");
        assertEquals("Directory contains files. Cannot be deleted.", game.processCommand("rmdir pictures"));

    }

}