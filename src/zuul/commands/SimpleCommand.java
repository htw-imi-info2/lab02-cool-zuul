package zuul.commands;

import zuul.GameStatus;
import zuul.commands.Command;

/**
 * A zuul.commands.SimpleCommand just returns its output,
 * The gameStatus is never changed.
 */
public class SimpleCommand extends Command
{
    String output;
    public SimpleCommand(String parameters, String output){
        super(parameters);
        this.output = output;
    }
    @Override
    public String commandImplementation(GameStatus _gameStatus){
        return output;
    }

}
