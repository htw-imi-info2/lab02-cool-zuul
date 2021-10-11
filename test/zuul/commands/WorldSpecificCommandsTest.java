package zuul.commands;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zuul.Game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 
 * @author  Barne Kleinen
 */
public class WorldSpecificCommandsTest
{
    private Game game;

    @BeforeEach
    public void setUp()
    {
        game = new Game("test/data/zuul.yml");
    }

    @Test
    public void testLook()
    {
        String actual = game.processCommand("look");
        assertTrue( actual.contains("You are outside the main entrance"));
        assertTrue( actual.contains("west"));
    }

    @Test
    public void showExits(){
        game.processCommand("go south");
        String result = game.processCommand("go north");
        assertTrue(result.contains("Exits:"));
        assertTrue(result.contains("east"));
        assertTrue(result.contains("south"));
        assertTrue(result.contains("west"));
        assertFalse(result.contains("north"));
    }

}
