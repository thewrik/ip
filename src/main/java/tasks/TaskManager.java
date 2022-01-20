package tasks;

import style.Style;

import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static String add(String taskName) {
        Task addedTask = new Task(taskName);
        taskList.add(addedTask);
        String prompt = String.format("added: %s", taskName);
        return prompt;
    }

    private static String list() {
        int task_no = 1;
        StringBuilder prompt = new StringBuilder("");
        for (Task t:taskList) {
            prompt.append(String.format("%d. %s\n", task_no++, t.toString()));
        }
        return prompt.toString().trim();
    }

    public static void process(String inputMessage) {
        if (inputMessage.equals("list")) {
            Style.printStylised(list());
        } else {
            Style.printStylised(add(inputMessage));
        }
    }
}
