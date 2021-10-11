package zuul;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.fail;
public class ZuulTest
{
    protected Game game;

    @BeforeEach
    public void setUp()
    {
        game = new Game("test/data/zuul.yml");
    }

    /**
     * helper method for completeWalkthrough - no changes needed
     */
    protected void goAndSee(String direction, String whatShouldBeContained){
        //when
        String result = game.processCommand("go "+direction);
        //then
        assertContains(result,whatShouldBeContained);
    }

    protected void assertContains(String result, String whatShouldBeContained){
        if (!result.contains(whatShouldBeContained))
            fail(result +"\n does not contain: --- "+whatShouldBeContained);
    }
}
