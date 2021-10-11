package zuul.world;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class World {
    private String name;

    private Room startRoom;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
    private Map<String, Room> rooms;
    private Map<String, Room> items;

    public Map<String, String> getConnections() {
        return connections;
    }

    private Map<String, String> connections;

    public World(){
        rooms = new LinkedHashMap<>();
        items = new LinkedHashMap<>();
        connections = new HashMap<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Map<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(String id, Room room) {
        rooms.put(id,room);
    }
    public void addItem(String id, Room room) {
        rooms.put(id,room);
    }

    public void addConnection(String back, String forth) {
        connections.put(back,forth);
        connections.put(forth,back);
    }

    public void addExit(String fromID, String direction, String toID) {
        Room roomFrom = rooms.get(fromID);
        Room roomTo = rooms.get(toID);
        roomFrom.setExit(direction,roomTo);
        String reverse = connections.get(direction);
        if (reverse != null)
            roomTo.setExit(reverse,roomFrom);
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }
}
