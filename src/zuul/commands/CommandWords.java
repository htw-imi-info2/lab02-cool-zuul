package zuul.commands;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds a map mapping all command words to functions that
 * return a new Instance of the according zuul.commands.Command implementation.
 * It is used to recognise commands as they are typed in.
 * The getCommand(commandWord,parameter) method returns a new instance of the
 * matching zuul.commands.Command (Instance of a concrete Subclass of zuul.commands.Command)
 * 
 * zuul.commands.CommandWords is an enum with just one instance.
 * This makes it easy to access it from anywhere/
 * all other classes in the program via
 * zuul.commands.CommandWords.INSTANCE
 *
 * @author Barne Kleinen, based on example by Michael Kölling and David J. Barnes
 * @version 2021.07.11
 */

import java.util.stream.*;
import java.util.*;
import java.util.function.Function;

public enum CommandWords{

    /**
     * Global Constant giving access to the one instance of this class.
     * (there is never the need for more than one instance, as it only
     * provides methods and stores no state).
     */
    INSTANCE;

    /*
     * Maps the CommandWord to a generator function that creates a new instance of the
     * command storing the parameter as a field.
     */
    private Map<String,Function<String,Command>> commands = new HashMap<>();

    /**
     * Constructor - initialise the command words.
     */
    private CommandWords(){
        commands.put("go", p -> new Go(p)); 
        commands.put("quit", p -> new Quit(p)); 
        commands.put("help", p -> new Help(p)); 
        commands.put("unknown", p -> new SimpleCommand(p, "I don't know what you mean...\n"));
        commands.put("read", p-> new SimpleCommand(p,"You are reading a book\n"));
        Function<String, Command> lookGenerator = (p) -> new Look(p);
        commands.put("look", lookGenerator);
        commands.put("x",lookGenerator);
        commands.put("back", p -> new Back(p));
        commands.put("beamer", p -> new Beamer(p));
        commands.put("take", p -> new Take(p));
        commands.put("drop", p -> new Drop(p));
        commands.put("inventory", p -> new Inventory(p));
        commands.put("i", p -> new Inventory(p));
    }

    /**
     * Returns an Instance of a Subclass of zuul.commands.Command implementing
     * the zuul.commands.Command matching commandWord.
     * If the commandWord is unknown, a default zuul.commands.SimpleCommand is returned.
     * This removes the necessity of handling a unknown command anywhere else.
     */
    public Command getCommand(String commandWord,String parameter){
        Function<String,Command> generator = commands.get(commandWord);
        if (generator == null) generator = commands.get("unknown");
        return generator.apply(parameter);
    }

    public String getValidCommands(){
        Collection<String> validCommands = commands.keySet();
        return validCommands.stream().collect(Collectors.joining(" ,"));
    }
}
