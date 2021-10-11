package zuul.commands;

import zuul.GameStatus;
import zuul.commands.Command;

public class Help extends Command
{
    public Help(String parameters){
        super(parameters);
    }
       
     /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
     public String commandImplementation(GameStatus gameStatus){
        return "You are lost. You are alone. You wander"
        +"\n"
        + "around at the university."
        +"\n"
        +"\n"
        +"Your command words are:"
        +"\n"
        +"   "
        + CommandWords.INSTANCE.getValidCommands()
        +"\n";
    }
}