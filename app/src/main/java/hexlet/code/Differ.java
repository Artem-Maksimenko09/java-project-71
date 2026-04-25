package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Differ {
    public static String generate() {
        return "";
    }

    public static void printFiles(String filepath1, String filepath2) throws IOException {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        var result = new HashMap<String, String>();
        result.put("1", content1);
        result.put("2", content2);
        System.out.print(result);
    }
}
