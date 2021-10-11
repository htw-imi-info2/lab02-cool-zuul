package zuul.commands;
import zuul.GameStatus;

public class Beamer extends Command
{
    public Beamer(String parameters){
        super(parameters);
    }

    public String commandImplementation(GameStatus gameStatus){
        if(!hasParameter()){
            return "to use the beamer, charge it with the current room ('beamer charge').\n"
            + "You can then go back to this room by typing 'beamer fire'\n";
        }

        switch(getParameter()){
            case "charge":
                gameStatus.chargeBeamer(); 
                return "The beamer was loaded with your current location.";
            case "fire" : 
                if (gameStatus.fireBeamer()) 
                return "The beamer beamed you back.\n"+gameStatus.getLocationDescription();
                else return "The beamer was not loaded.\n";
            default: 
                return "The beamer can be charge d or fire d.";
            }
        }
    }
