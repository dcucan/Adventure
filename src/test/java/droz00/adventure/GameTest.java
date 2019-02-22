package droz00.adventure;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void gameCanBeWon(){
        Game game = new Game();
        game.processCommand("cd safari");
        game.processCommand("cut windows10.vhd");
        game.processCommand("cd macos");
        game.processCommand("cd trash");
        game.processCommand("paste windows10.vhd");
        game.processCommand("cd macos");
        game.processCommand("cd safari");
        game.processCommand("cut bootcamp_assistant.vhd");
        game.processCommand("check");
        assertEquals(true,game.isWon());
        assertEquals(true,game.isOver());
    }

    @Test
    public void gameCanBeLost(){
        Game game = new Game();
        game.processCommand("cd safari");
        game.processCommand("check");
        assertEquals(false,game.isWon());
        assertEquals(true,game.isOver());
    }

}