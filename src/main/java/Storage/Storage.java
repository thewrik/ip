package Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import  java.nio.file.Files;

public class Storage {
    private static Path storagePath;
    private static File storageFile;

    public static void  initialise() {
        try {
            Path storageFolderPath = Paths.get("data");
            if (Files.notExists(storageFolderPath)) Files.createDirectory(storageFolderPath);
            Path storageFilePath = storageFolderPath.resolve(Paths.get("tasks.txt"));
            if (Files.notExists(storageFilePath)) Files.createFile(storageFilePath);
            Storage.storagePath = storageFilePath;
            Storage.storageFile = Storage.storagePath.toFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void write(String message) {
        try {
            FileWriter fileWriter = new FileWriter(storageFile.getAbsolutePath());
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String save(String message) {
        write(message);

        return String.format("Task List auto-saved to %s", storageFile.getAbsolutePath());
    }
}
