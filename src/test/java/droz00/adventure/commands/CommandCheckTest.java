package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandCheckTest {

    @Test
    public void internetBrowserIsGoogle() {
        Game game = new Game();
        game.processCommand("cd safari");
        game.processCommand("cut windows10.vhd");
        game.processCommand("cd macos");
        game.processCommand("cd trash");
        game.processCommand("paste windows10.vhd");
        game.processCommand("cd macos");
        game.processCommand("cd safari");
        game.processCommand("cut bootcamp_assistant.vhd");
        assertEquals("You have finaly set the internet browser to Google, well done!", game.processCommand("check"));
    }

    @Test
    public void internetBrowserIsSafeFinder(){
        Game game = new Game();
        game.processCommand("cd safari");
        assertEquals("Your internet browser is still Safe finder, boohoo :(", game.processCommand("check"));
    }
}