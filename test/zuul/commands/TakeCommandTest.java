package zuul.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zuul.ZuulTest;


public class TakeCommandTest extends ZuulTest
{
    public TakeCommandTest()
    {
       super();
    }
    @BeforeEach
    public void goToPub(){
        goAndSee("west","cacao");
    }
    @Test
    public void testTake(){
        String actual = game.processCommand("take cacao");
        assertContains( actual,"You took the cacao");
    }
    
    @Test
    public void testTakeNotThere(){
        String actual = game.processCommand("take beer");
        assertContains( actual,"There is no beer!");
    }
    

}
