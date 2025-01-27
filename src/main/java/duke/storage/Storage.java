package duke.storage;

import duke.tasks.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private static Path storagePath;
    private static File storageFile;

    /**
     * Initialises the file location where the saved state of the task manager is to be stored.
     */
    public static void initialise() {
        try {
            Path storageFolderPath = Paths.get("data");
            if (Files.notExists(storageFolderPath)) Files.createDirectory(storageFolderPath);
            Path storageFilePath = storageFolderPath.resolve(Paths.get("tasks.txt"));
            if (Files.notExists(storageFilePath)) Files.createFile(storageFilePath);
            Storage.storagePath = storageFilePath;
            Storage.storageFile = Storage.storagePath.toFile();
            loadTasks();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void loadTasks() {
        try {
            List<String> savedTaskList = Files.readAllLines(Storage.storagePath);
            TaskManager.generateAndAddFromFile(savedTaskList);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Writes the message passed as a parameter to the file the class was initialised with.
     *
     * @param message The message content to be saved.
     */
    public static void save(String message) {
        try {
            FileWriter fileWriter = new FileWriter(storageFile.getAbsolutePath());
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
