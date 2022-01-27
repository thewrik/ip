package duke.tasks;

import duke.storage.Storage;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCounter = 0;

    static String countTasks() {
        String pluralSuffix = taskCounter == 1 ? "" : "s";
        return String.format("%s task%s", taskCounter, pluralSuffix);
    }

    static String add(Task task) {
        taskList.add(task);
        taskCounter++;

        return String.format("Great, have added the following task for you:\n%s\nNow you have %s.", task, countTasks());
    }

    static Task get(int index) {
        return taskList.get(index - 1);
    }

    static String list() {
        int task_no = 1;
        StringBuilder prompt = new StringBuilder();

        prompt.append(String.format("You have %s in the list\n", countTasks()));

        for (Task t:taskList) {
            prompt.append(String.format("%d. %s\n", task_no++, t.toString()));
        }
        return prompt.toString().trim();
    }

    static void save() {
        int task_no = 1;
        StringBuilder currentListState = new StringBuilder();

        for (Task t:taskList) {
            currentListState.append(String.format("%d. %s\n", task_no++, t.toString()));
        }

        Storage.save(currentListState.toString());
    }

    static Task delete(int index) {
        Task deletedTask = taskList.remove(index - 1);
        taskCounter--;
        return deletedTask;
    }
}

