package zuul.commands;
import zuul.*;

public class Back extends Command
{
    public Back(String parameters){
        super(parameters);
    }

    public String commandImplementation(GameStatus gameStatus){
        if (gameStatus.goBack())
            return gameStatus.getLocationDescription();
        else
            return "You want to go back, but can't remember where you came from!";
    }
}
