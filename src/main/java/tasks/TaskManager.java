package tasks;

import style.Style;

import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCounter = 0;

    private static String countTasks() {
        String pluralSuffix = taskCounter == 1 ? "" : "s";
        return String.format("%s task%s", taskCounter, pluralSuffix);
    }

    private static String add(String taskName) {
        Task addedTask;
        if (taskName.startsWith(Commands.todo.toString())) {
            addedTask = new ToDoTask(taskName.substring(Commands.todo.toString().length() + 1));
        } else if (taskName.startsWith(Commands.deadline.toString())){
            addedTask = new DeadlineTask(taskName.substring(Commands.deadline.toString().length() + 1));
        } else if (taskName.startsWith(Commands.event.toString())){
            addedTask = new EventTask(taskName.substring(Commands.event.toString().length() + 1));
        } else {
            addedTask = new Task(taskName);
        }
        taskList.add(addedTask);
        taskCounter++;

        String prompt = String.format("Great, have added the following task for you:\n%s\nNow you have %s.", addedTask, countTasks());
        return prompt;
    }

    private static String list() {
        int task_no = 1;
        StringBuilder prompt = new StringBuilder("");

        prompt.append(String.format("You have %s in the list", countTasks()));

        for (Task t:taskList) {
            prompt.append(String.format("%d. %s\n", task_no++, t.toString()));
        }
        return prompt.toString().trim();
    }

    private static String mark(int index) {
        Task targetTask = taskList.get(index - 1);
        targetTask.mark();
        StringBuilder prompt = new StringBuilder("Well done! I have marked this task as done.\n");
        prompt.append(targetTask.toString());
        return prompt.toString().trim();
    }

    private static String unmark(int index) {
        Task targetTask = taskList.get(index - 1);
        targetTask.unmark();
        StringBuilder prompt = new StringBuilder("No worries, I have unmarked this task, good luck!\n");
        prompt.append(targetTask.toString());
        return prompt.toString().trim();
    }

    public static void process(String inputMessage) {
        if (inputMessage.equals("list")) {
            Style.printStylised(list());
        } else if (inputMessage.startsWith("mark")) {
            Style.printStylised(mark(Integer.valueOf(inputMessage.substring("mark".length() + 1))));
        } else if (inputMessage.startsWith("unmark")) {
            Style.printStylised(unmark(Integer.valueOf(inputMessage.substring("unmark".length() + 1))));
        } else {
            Style.printStylised(add(inputMessage));
        }
    }
}
