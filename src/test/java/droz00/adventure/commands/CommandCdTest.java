package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandCdTest {

    @Test
    public void changesDirectory(){
        Game game = new Game();
        game.processCommand("cd safari");
        assertEquals("safari",game.getCurrentPlace().getName());
    }

}