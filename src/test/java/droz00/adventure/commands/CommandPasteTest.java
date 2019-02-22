package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandPasteTest {

    @Test
    public void showsThedescription() {
        Game game = new Game();
        assertEquals("Paste file(s) to current directory", game.processCommand("paste"));
    }

}