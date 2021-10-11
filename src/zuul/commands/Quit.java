package zuul.commands;

import zuul.GameStatus;
import zuul.commands.Command;

public class Quit extends Command
{
    public Quit(String parameters){
        super(parameters);
    }

    /** 
     * "zuul.commands.Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return null, if this command quits the game, anything else otherwise.
     */
    @Override
    public String commandImplementation(GameStatus gameStatus){

        if(hasParameter()) {
            return "zuul.commands.Quit what?";
        }
        else {
            gameStatus.quit(); // signal that we want to quit
            return "Thank you for playing.  Good bye.\n";  
        }
    }
}
