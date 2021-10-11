package zuul.world.api;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import zuul.world.persistence.FileReader;
import zuul.world.World;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * add org.yaml:snakeyaml:1.29
 */
public class ReadWorld {

    @Test
    public void testEmptyWorld(){
        Yaml yaml = new Yaml(new Constructor(World.class));
        World world = yaml.load("name: Campus");
        assertEquals("Campus",world.getName(),"world name");
    }
    @Test
    public void testRooms(){
        // this needs Java 15 and up
        String yaml = """
    name: Campus
    rooms:
      room1:
        description: You are in a forrest 1
      room2:
        description: You are in a forrest 2
      room3:
        description: You are in a forrest 3
""";
        Yaml snakeyaml = new Yaml();
        Map<String,Object> readYaml = snakeyaml.load(yaml);
        System.out.println(readYaml);
        assertEquals("Campus",readYaml.get("name"));
        Map<String,Map> rooms = (Map<String,Map>) readYaml.get("rooms");
        System.out.println(rooms);
        assertEquals(3,rooms.size());
        Map<String,Map> room = (Map<String,Map>)rooms.get("room2");
        System.out.println(room);
        assertEquals("You are in a forrest 2",room.get("description"));
    }
    @Test
    public void testCampusFile(){
        String yaml = FileReader.readFileToString("test/data/campus.yml");
        Yaml snakeyaml = new Yaml();
        Map<String,Object> readYaml = snakeyaml.load(yaml);
        System.out.println(readYaml);
        assertEquals("Campus",readYaml.get("name"));
        Map<String,Map> rooms = (Map<String,Map>) readYaml.get("rooms");
        System.out.println(rooms);
        assertEquals(6,rooms.size());
        Map<String,Map> room = (Map<String,Map>)rooms.get("theater");
        System.out.println(room);
        assertEquals("in a lecture theater",room.get("description"));

    }

}
