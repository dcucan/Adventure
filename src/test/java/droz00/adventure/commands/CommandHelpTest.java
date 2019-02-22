package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandHelpTest {

    @Test
    public void writesHelpForCommand(){
        Game game = new Game();
        assertEquals("Change directory, use cd + name of directory where you want to go",game.processCommand("help cd"));
    }

}