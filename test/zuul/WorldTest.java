package zuul;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * 
 * @author  Barne Kleinen
 */
public class WorldTest extends ZuulTest
{

    /**
     * This test should keep working
     */
    @Test
    public void goWODirectionShouldShowError()
    {
        assertEquals("zuul.commands.Go where?", game.processCommand("go"));
    }

    /**
     * Adapt this to your new world
     */
    @Test
    public void completeWalkthrough()
    {
        goAndSee("east",  "lecture theater");
        goAndSee("west",  "main entrance");
        goAndSee("west",  "campus pub");
        goAndSee("down",  "cellar");
        goAndSee("up",  "campus pub");
        goAndSee("east",  "main entrance");
        goAndSee("south", "computing lab");
        goAndSee("east",  "admin office");
        goAndSee("west",  "computing lab");
        goAndSee("north", "main entrance");
    }

    
     /**
     * Adapt this to your new world
     */
    @Test
    public void congratsAllRooms()
    {
        goAndSee("east",  "lecture theater");
        goAndSee("west",  "main entrance");
        goAndSee("west",  "campus pub");
        goAndSee("down",  "cellar");
        goAndSee("up",  "campus pub");
        goAndSee("east",  "main entrance");
        goAndSee("south", "computing lab");
        goAndSee("east",  "Congrats");
        goAndSee("west",  "computing lab");
        goAndSee("north", "main entrance");
    }


}
