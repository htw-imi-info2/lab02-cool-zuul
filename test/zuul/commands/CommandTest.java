package zuul.commands;

import org.junit.jupiter.api.Test;
import zuul.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest
{
    Game game ;
    /**
     * Default constructor for test class zuul.commands.CommandTest
     */
    public CommandTest()
    {
        game = new Game("test/data/zuul.yml");
    }

    @Test
    public void testReadCommand(){
        String result = game.processCommand("read");
        assertEquals("You are reading a book\n",result);
    }

    @Test
    public void testHelp()
    {
        String actual = game.processCommand("help");
        assertTrue( actual.contains("Your command words are:"));
        assertTrue( actual.contains("go"));
        assertTrue( actual.contains("quit"));
        assertTrue( actual.contains("help"));

    }
    
     @Test
    public void testHelpAdditionalCommands()
    {
        String actual = game.processCommand("help");
        assertTrue( actual.contains("read"));
        assertTrue( actual.contains("look"));
    }

    @Test
    public void testUnknown(){
        assertEquals("I don't know what you mean...\n",game.processCommand("murks"));
    }

    @Test
    public void testQuit(){
        assertEquals("zuul.commands.Quit what?",game.processCommand("quit not really"));
        assertEquals("Thank you for playing.  Good bye.\n",game.processCommand("quit"));
    }
    
    @Test
    public void testBack(){
        game.processCommand("go west");
        String actual = game.processCommand("back");
        assertTrue( actual.contains("main entrance"),"main entrance should be contained: "+actual);
        actual = game.processCommand("back");
        assertTrue( actual.contains("can't remember"),"can't remember should be contained");
        
      
    }
    

}

