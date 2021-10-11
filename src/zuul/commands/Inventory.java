package zuul.commands;
import zuul.GameStatus;

public class Inventory extends Command
{
    public Inventory(String parameters){
        super(parameters);
    }
    public String commandImplementation(GameStatus gameStatus){
        return gameStatus.getInventoryDescription();
    }
}
