package droz00.adventure.commands;

import droz00.adventure.Game;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLsTest {

    @Test
    public void showsListOfTheFilesInCurrentDirectory(){
        Game game = new Game();
        assertEquals("directories: safari chrome windows finder trash\n" +
                "files: Picture.jpeg Java.pdf",game.processCommand("ls"));
    }

}