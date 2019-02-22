package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandMkdirTest {

    @Test
    public void createsNewDirectory(){
        Game game = new Game();
        assertEquals(CommandMkdir.SUCCESS_MESSAGE, game.processCommand("mkdir new"));
    }

    @Test
    public void cannotCreateDirectoryThatExists(){
        Game game = new Game();
        game.processCommand("mkdir new");
        assertEquals(CommandMkdir.ALREADY_EXISTS_MESSAGE, game.processCommand("mkdir new"));
    }



}