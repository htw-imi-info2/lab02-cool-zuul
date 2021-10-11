/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
package zuul.world;

import zuul.items.Item;
import zuul.items.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Room
{
    private static int roomCounter = 0;

    public String getName() {
        return name;
    }

    private String name;
    private String description;

    public Inventory getInventory() {
        return items;
    }

    private Map<String,Room> exits = new HashMap<>();
    private Inventory items = new Inventory();

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        roomCounter++;
    }
    public static int getRoomCounter(){return roomCounter;}
    public static void resetCounter(){roomCounter = 0;}
    public void setExit(String d, Room r){exits.put(d,r);}

    public void addItem(Item i){items.add(i);}
    public Item removeItem(String itemName){return items.remove(itemName);}
    public Item getItem(String itemName){return items.get(itemName);}

    public Room getExit(String d){return exits.get(d);}

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description + "\n"
        + items.getDescription() +"\n"
        + getExitDescription();
    }

    public String getExitDescription(){
        return "Exits: " + exits.keySet().stream().collect(Collectors.joining(","));
    }
    public Map<String, Room> getExits() {
        return exits;
    }
}
