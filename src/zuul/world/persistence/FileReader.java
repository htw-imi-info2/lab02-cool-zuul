package zuul.world.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    public static String readFileToString(String filename){
        Path path = Paths.get(filename);
        try {
            List<String> strList = Files.readAllLines(path);
            return String.join("\n", strList);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
