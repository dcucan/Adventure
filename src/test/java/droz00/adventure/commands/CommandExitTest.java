package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandExitTest {

    @Test
    public void itStopsTheGame(){
        Game game = new Game();
        game.processCommand("exit");
        assertEquals(true,game.isOver());
    }

}