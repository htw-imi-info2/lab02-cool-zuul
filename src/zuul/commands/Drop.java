package zuul.commands;

import zuul.GameStatus;
import zuul.items.Item;

public class Drop extends Command
{
    public Drop(String parameter){
        super(parameter);
    }

    @Override
    public String commandImplementation(GameStatus gameStatus){

        if(!hasParameter()) {
            // if there is no second word, we don't know what to take...
            return "zuul.commands.Drop what?";
        }

        String itemName = getParameter();

        // Try to take the item.
        Item item = gameStatus.getInventory().remove(itemName);
        

        if (item == null) {
            return"You have no "+itemName+"!";
        }
        else {
            gameStatus.getLocation().addItem(item);
            return "You dropped the "+ item.getName();
        }
    }
}
