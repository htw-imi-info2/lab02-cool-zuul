package zuul.commands;

import zuul.GameStatus;

/**
 * This class is part of the "World of Zuul" application.
* "World of Zuul" is a very simple, text based adventure game.
*
* As in the original version, this class holds information about a
* command that was issued by the user.
* A command currently consists of two strings: a command word and a second
* word / parameter (for example, if the command was "take map", then the two strings
* obviously are "take" and "map").
*
*
*/
public abstract class Command
{
    private String parameter;

    public Command(String parameter)
    {
        this.parameter = parameter;
    }

    protected boolean hasParameter(){
        return parameter != null;
    }
    String getParameter(){
        return parameter;
    }

    /**
     * this method needs to be overwritten by each subclass
     * to actually implement the command.
     */
    public abstract String commandImplementation(GameStatus gameStatus);

     /**
     * this method needs to be overwritten by each subclass
     * to actually implement the command.
     */
    public String process(GameStatus gameStatus){
        String result = commandImplementation(gameStatus);
        return result;
    }

}
