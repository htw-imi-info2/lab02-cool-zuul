package zuul.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zuul.items.Item;
import zuul.world.persistence.WorldAdapter;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WriteWorldTest {
    WorldAdapter wa = new WorldAdapter();
    World world;
    @BeforeEach
    void setUp() {
        World originalWorld =  wa.readFromFile("test/data/campus.yml");
        String yaml = wa.export(originalWorld);
        System.out.println(yaml);
        world = wa.fromString(yaml);
    }

    @Test
    public void checkItemInPub(){
        Map<String, Room> rooms = world.getRooms();
        Room room = rooms.get("pub");
        Item mug = room.getItem("mug");
        assertNotNull(mug);
    }

    @Test
    public void checkTheaterConnection(){
        Map<String, Room> rooms = world.getRooms();
        Room room = rooms.get("theater");
        Room outside  = room.getExit("west");
        assertNotNull(outside);
    }
}
