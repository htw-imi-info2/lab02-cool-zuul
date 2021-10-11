package zuul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static zuul.TestHelper.assertContains;
import static zuul.TestHelper.assertDoesNotContain;

public class ItemsMultipleTest
{
    Game game;
    @BeforeEach
    public void setUp()
    {
        game = new Game("test/data/multipleItems.yml");
    }
    @Test
    public void testMilkInThePub()
    {
        String output = game.processCommand("go west");
        assertContains(output,"a bottle of milk" );// milk in the pub
    }
    @Test
    public void testSeeTwoItems()
    {
        String output = game.processCommand("look");
        assertContains(output,"coffee");
        assertContains(output,"a bottle of milk"); // cacao in the lab
    }
    @Test
    public void testDistinguishTwoItems()
    {
        String output = game.processCommand("go south");
        assertContains(output,"cacao"); // cacao in the lab
        assertContains(output,"cacao1"); // cacao in the lab
    }

    @Test
    public void testPickUpSecond()
    {
        String output = game.processCommand("go south");
        output = game.processCommand("take cacao1");

        assertDoesNotContain(output,"cacao1"); // cacao in the lab
    }


}
