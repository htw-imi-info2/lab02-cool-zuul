package zuul.commands;

import zuul.GameStatus;
import zuul.items.Item;

public class Take extends Command
{
    public Take(String parameter){
        super(parameter);
    }

    @Override
    public String commandImplementation(GameStatus gameStatus){

        if(!hasParameter()) {
            // if there is no second word, we don't know what to take...
            return "zuul.commands.Take what?";
        }

        String itemName = getParameter();

        // Try to take the item.
        Item item = gameStatus.getLocation().removeItem(itemName);

        if (item == null) {
            return "There is no "+itemName+"!";
        }
        int inventoryWeight = gameStatus.getInventory().getWeight();

        int carryWeight = gameStatus.maxCarryWeight();
        if (item.getWeight()+gameStatus.getInventory().getWeight() > gameStatus.maxCarryWeight()){
            return String.format("You cannot pick up the %s - it's too heavy.\nYou are already carrying %f kg!",
                    itemName,0.001*inventoryWeight);
        }
        else {
            gameStatus.addItemToInventory(item);
            return "You took the "+ item.getName();
        }
    }
}
