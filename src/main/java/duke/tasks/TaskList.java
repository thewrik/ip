package duke.tasks;

import duke.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Gets the number of tasks stored in the Task List.
     *
     * @return the size of the task list, i.e. the number of tasks stored in the Task List.
     */
    static String countTasks() {
        String pluralSuffix = taskList.size() == 1 ? "" : "s";
        return String.format("%s task%s", taskList.size(), pluralSuffix);
    }

    /**
     * Adds a Task object to the TaskList.
     *
     * @param task The task to be added to the list.
     *
     * @return a message acknowledging the addition of the task, including the task details.
     */
    static String add(Task task) {
        taskList.add(task);

        return String.format("Great, have added the following task for you:\n%s\nNow you have %s.", task, countTasks());
    }


    /**
     * Fetches a task by index. (1-indexed list).
     *
     * @param index The integer passed to get the task at that index.
     *
     * @return the task at the given index.
     */
    static Task get(int index) {
        return taskList.get(index - 1);
    }

    /**
     *  Returns a string representation of the list of tasks.
     */
    static String list() {
        int task_no = 1;
        StringBuilder prompt = new StringBuilder();

        prompt.append(String.format("You have %s in the list\n", countTasks()));

        for (Task t : taskList) {
            prompt.append(String.format("%d. %s\n", task_no++, t.toString()));
        }
        return prompt.toString().trim();
    }

    /**
     * Saves the current state of the list using the Storage component.
     */
    static void save() {
        int task_no = 1;
        StringBuilder currentListState = new StringBuilder();

        for (Task t : taskList) {
            currentListState.append(String.format("%d. %s\n", task_no++, t.toString()));
        }

        Storage.save(currentListState.toString());
    }

    /**
     * Deletes the Task at a certain index (1-indexed list).
     *
     * @param index The integer passed to get the task at that index.
     * @return A message acknowledging the deletion with the deleted task included.
     */
    static Task delete(int index) {
        Task deletedTask = taskList.remove(index - 1);
        return deletedTask;
    }

    static void reinitialise() {
        taskList.clear();
    }

    /**
     * This is a method that finds all the tasks in the task list which contain the keyword
     * in the task name as a constituent word.
     *
     * @param taskKeyword  The keyword being searched for.
     * @return A string containing the tasks found.
     */
    public static String find(String taskKeyword) {
        List<Task> filteredTasks = taskList.stream()
                .filter(
                        task -> Arrays.stream(task
                                .toString()
                                .toLowerCase()
                                .split(" "))
                                .anyMatch(word -> word.equals(taskKeyword.toLowerCase())))
                .collect(Collectors.toList());

        StringBuilder foundStrings = new StringBuilder("Here are the tasks found with the given keyword.\n");

        int filteredTaskCounter = 1;
        for (Task filteredTask :filteredTasks) {
            foundStrings.append(String.format("%s. %s\n", filteredTaskCounter++, filteredTask));
        }
        return foundStrings.toString().trim();
    }

    /**
     * This is a method that finds all the tasks in the task list which contain the keyword
     * in the task name as a constituent word, or as a substring of a constituent word.
     *
     * @param taskKeyword  The keyword being searched for.
     * @return A string containing the tasks found.
     */
    public static String findBetter(String taskKeyword) {
        List<Task> filteredTasks = taskList.stream()
            .filter(task -> task.toString().toLowerCase().contains(taskKeyword.toLowerCase()))
            .collect(Collectors.toList());

        StringBuilder foundStrings = new StringBuilder("Here are the tasks found after Better Search with the given keyword.\n");

        int filteredTaskCounter = 1;
        for (Task filteredTask :filteredTasks) {
            foundStrings.append(String.format("%s. %s\n", filteredTaskCounter++, filteredTask));
        }
        return foundStrings.toString().trim();
    }
}
