package zuul;

import zuul.commands.Command;
import zuul.commands.Parser;
import zuul.world.Room;
import zuul.world.World;
import zuul.world.persistence.WorldAdapter;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Game
{
    private Parser parser;
    private GameStatus gameStatus;
    private World world ;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        this("data/zuul.yml");
    }
    public Game(String worldFileName)
    {
        Room.resetCounter();
        createRooms(worldFileName);
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(String worldFileName)
    {
        world = new WorldAdapter().readFromFile(worldFileName);
        gameStatus = new GameStatus(world.getStartRoom());

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        while (gameStatus.isPlaying()) {
            Command command = parser.getCommand();
            String output = command.process(gameStatus);
            System.out.println(output);
        }

    }

    /**
     * This is a further method added by BK to
     * provide a clearer interface that can be tested:
     * Game processes a commandLine and returns output.
     * @param commandLine - the line entered as String
     * @return output of the command
     */
    public String processCommand(String commandLine){
        Command command = parser.getCommand(commandLine);
        return command.process(gameStatus);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println(world.getDescription());

        System.out.println(gameStatus.getLocationDescription());
        System.out.println();
    }
    public static void playInstantly(){new Game().play();}

}
