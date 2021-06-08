package dataImport.csvParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteFiles {
    public static void delete(String name){

        try {
            Files.deleteIfExists(Path.of(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
