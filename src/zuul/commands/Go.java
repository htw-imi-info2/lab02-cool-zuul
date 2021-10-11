package zuul.commands;

import zuul.GameStatus;
import zuul.world.Room;


public class Go extends Command
{
    public Go(String parameter){
        super(parameter);
    }

    @Override
    public String commandImplementation(GameStatus gameStatus){

        if(!hasParameter()) {
            // if there is no second word, we don't know where to go...
            return "zuul.commands.Go where?";
        }

        String direction = getParameter();

        // Try to leave current room.
        Room nextRoom = gameStatus.getLocation().getExit(direction);

        if (nextRoom == null) {
            return"There is no door!";
        }
        else {
            String r = gameStatus.setLocation(nextRoom);
            return r + gameStatus.getLocationDescription();
        }
    }
}
