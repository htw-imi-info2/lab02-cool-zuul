package zuul;
import zuul.items.Inventory;
import zuul.items.Item;
import zuul.world.Room;

import java.util.HashSet;
import java.util.Set;

public class GameStatus
{
    private Room currentRoom;
    private Room lastRoom;
    private Room beamer;
    private Set<Room> visited;
    private Inventory inventory;
    
    public GameStatus(Room initialRoom){
        inventory = new Inventory();
        visited = new HashSet<>();
        setLocation(initialRoom);
    }

    public String setLocation(Room room){
        lastRoom = currentRoom;
        currentRoom = room; 
        return recordVisited(room);
    }

    String visitedAllRooms = "\nCongrats! You've visited all Rooms";
    private String recordVisited(Room room){
        
        visited.add(room);
        
        if (visited.size() == Room.getRoomCounter()){
            String result = visitedAllRooms;
            visitedAllRooms = ""; // should never be shown again!
            return result;
        }
        return "";
    }

    public Room getLocation(){
        return currentRoom;
    }

    public boolean goBack(){
        if (lastRoom == null)
            return false;
        currentRoom = lastRoom;
        lastRoom = null;
        return true;
    }

    public void chargeBeamer(){
        beamer = currentRoom;
    }

    public boolean fireBeamer(){
        if (beamer == null) return false;
        setLocation(beamer);
        beamer = null;
        return false;
    }

    public String getLocationDescription(){
        return "You are " +currentRoom.getDescription();
    }

    private boolean playing = true;
    public void quit(){playing = false;}

    public boolean isPlaying(){return playing;}

    public void addItemToInventory(Item item){inventory.add(item);}

    public String getInventoryDescription(){return "Your inventory:\n"+inventory.getDescription();}

    public Inventory getInventory(){return inventory;}

    public int maxCarryWeight() {
        // 20kg
        return 20000;
    }
}
