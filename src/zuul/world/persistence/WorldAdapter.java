package zuul.world.persistence;

import org.yaml.snakeyaml.Yaml;
import zuul.items.Item;
import zuul.world.Room;
import zuul.world.World;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorldAdapter {
    String yaml;
    String fileName;
    Map<String,Object> readYaml;

    public World readFromFile(String fileName){
        this.fileName = fileName;
        this.yaml = FileReader.readFileToString(fileName);
        return fromString(this.yaml);
   }
    public World fromString(String yaml) {
        Yaml snakeyaml = new Yaml();
        readYaml = snakeyaml.load(yaml);
        World world = new World();
        addWorldFields(world,readYaml);
        addRooms(world,readYaml);
        addItems(world,readYaml);
        addConnections(world,readYaml);
        addExits(world,readYaml);
        addStartRoom(world,readYaml);
        return world;
    }

    private void addWorldFields(World world, Map<String, Object> readYaml) {
        world.setName((String)readYaml.get("name"));
        world.setDescription((String)readYaml.get("description"));
    }
    private void addStartRoom(World world, Map<String, Object> readYaml){
        String roomID = (String)readYaml.get("start");
        Room room = world.getRooms().get(roomID);
        world.setStartRoom(room);
    }
    private void addWorldFieldsExport(Map<String, Object> worldExport, World world) {
        worldExport.put("name",world.getName());
        worldExport.put("description",world.getDescription());
    }

    private void addRooms(World world, Map<String, Object> readYaml) {
        Map<String, Map> rooms = (Map<String, Map>) readYaml.get("rooms");
        for (String id : rooms.keySet()) {
            Map<String, String> room = (Map<String, String>) rooms.get(id);
            String description = (String) room.get("description");
            world.addRoom(id, new Room(id,description));
        }
    }

    private void addConnections(World world, Map<String, Object> readYaml) {
        Map<String, String> connections = (Map<String, String>) readYaml.get("connections");
        for (String forth : connections.keySet()) {
            String back = (String) connections.get(forth);
            world.addConnection(back,forth);
        }
    }

    private void addConnectionsExport(Map<String, Object> worldExport, World world) {
            worldExport.put("connections",world.getConnections());
    }
    private void addRoomsExport(Map<String, Object> worldExport, World world) {
        Map<String, Map> roomsExport = new LinkedHashMap<>();
        Map<String, Map> exitsExport = new LinkedHashMap<>();
        Map<String, Map> itemsExport = new LinkedHashMap<>();

        for (String id : world.getRooms().keySet()) {
            Room room = world.getRooms().get(id);
            Map<String, String> roomExport = new LinkedHashMap<>();
            roomExport.put("name", id);
            roomExport.put("description", room.getDescription());
            roomsExport.put(id, roomExport);

            Map<String, String> exitPairs = new LinkedHashMap<>();
            Map<String, Room> exits = room.getExits();
            for (String direction : exits.keySet()) {
                Room targetRoom = exits.get(direction);
                exitPairs.put(direction,targetRoom.getName());
            }
            exitsExport.put(id, exitPairs);

            for (Item item : room.getInventory()) {
                Map<String, Object> itemExport = new LinkedHashMap<>();
                itemExport.put("description", item.getDescription());
                itemExport.put("weight", item.getWeight());
                itemExport.put("room", id);
                itemsExport.put(item.getName(), itemExport);
            }
        }

        worldExport.put("rooms", roomsExport);
        worldExport.put("exits", exitsExport);
        worldExport.put("items", itemsExport);
    }


    private void addExits(World world, Map<String, Object> readYaml) {
        Map<String, Map> exits = (Map<String, Map>) readYaml.get("exits");
        for (String roomID : exits.keySet()) {
            Map<String, String> exitPairs = (Map<String, String>) exits.get(roomID);
            for (String direction : exitPairs.keySet()) {
                String toRoomID = (String) exitPairs.get(direction);
            world.addExit(roomID,direction,toRoomID);
        }

        Map<String, String> connections = (Map<String, String>) readYaml.get("connections");
        for (String forth : connections.keySet()) {
            String back = (String) connections.get(forth);
            world.addConnection(back,forth);
        }
    }
    }
    private void addItems(World world, Map<String, Object> readYaml) {
        Map<String, Map> items = (Map<String, Map>) readYaml.get("items");
        for (String name : items.keySet()) {
            Map<String, Object> itemyaml = (Map<String, Object>) items.get(name);
            String description = (String) itemyaml.get("description");
            int weight  = (int) itemyaml.get("weight");
            Item item = new Item(name, description, weight);
            Object rooms = itemyaml.get("room");
            addItemToRooms(world,item,rooms);
        }
    }

    private void addItemToRooms(World world, Item item, List rooms) {
        List<String> rooms2 = (List<String>)rooms;
        for(String roomID: rooms2){
            addItemToRooms(world,item,roomID);
        }
    }
    private void addItemToRooms(World world, Item item, String roomID) {
        Room room = world.getRooms().get(roomID);
        room.addItem(item);
    }
    private void addItemToRooms(World world, Item item, Object rooms) {
        // necessary as Java uses static binding here.
        if(rooms instanceof String) {
            addItemToRooms(world, item, (String) rooms);
        } else if (rooms instanceof List){
            addItemToRooms(world, item, (List<String>) rooms);
        } else {
            throw new RuntimeException("should not reach this.");
        }
    }

    public String export(World world) {
        Yaml yaml = new Yaml();

        Map<String,Object> worldExport = new LinkedHashMap<>();
        addWorldFieldsExport(worldExport,world);
        addConnectionsExport(worldExport,world);
        addRoomsExport(worldExport,world);

        String output = yaml.dump(worldExport);
        return output;
    }
}
