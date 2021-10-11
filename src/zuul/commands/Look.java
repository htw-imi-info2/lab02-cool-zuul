package zuul.commands;

import zuul.GameStatus;
import zuul.commands.Command;

public class Look extends Command
{
    public Look(String parameters){
        super(parameters);
    }
    public String commandImplementation(GameStatus gameStatus){
        return gameStatus.getLocationDescription();
    }
}
