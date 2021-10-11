package zuul.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zuul.Game;

import static zuul.TestHelper.assertContains;

public class ItemWeightTest {

    Game game;
        @BeforeEach
        public void setUp(){
            game = new Game("test/data/weightLimit.yml");
        }
        @Test
        public void testTake15(){
            String actual = game.processCommand("take stone");
            actual = game.processCommand("take stone");
            actual = game.processCommand("take stone");
           // actual = game.processCommand("look");
            assertContains( actual,"You took the stone");
        }
    @Test
    public void testTake20(){
        String actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        // actual = game.processCommand("look");
        assertContains( actual,"You took the stone");
    }
    @Test
    public void testTake25IsTooHeavy(){
        String actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        actual = game.processCommand("take stone");
        assertContains( actual,"too heavy");
    }



}
