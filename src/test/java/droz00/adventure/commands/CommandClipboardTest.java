package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandClipboardTest {

    @Test
    public void showsFilesInClipboard(){
        Game game = new Game();
        game.processCommand("cut Java.pdf");
        assertEquals(" Java.pdf",game.processCommand("clipboard"));
    }

    @Test
    public void clipboardCanBeEmpty(){
        Game game = new Game();
        assertEquals("--no file(s) in clipboard--", game.processCommand("clipboard"));
    }


}