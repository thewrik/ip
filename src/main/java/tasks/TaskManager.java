package tasks;

import style.Style;
import taskManagerExceptions.InvalidCommandExcpetion;
import taskManagerExceptions.InvalidParameterException;

import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCounter = 0;

    private static String countTasks() {
        String pluralSuffix = taskCounter == 1 ? "" : "s";
        return String.format("%s task%s", taskCounter, pluralSuffix);
    }

    private static String add(String taskName) throws InvalidCommandExcpetion, InvalidParameterException {
        Task addedTask;
        taskName = taskName.trim();

        if (taskName.startsWith(Commands.todo.toString())) {
            if (taskName.length() == Commands.todo.toString().length()) {
                throw new InvalidParameterException("A Todo task must have a task name.\nPlease try again");
            }
            addedTask = new ToDoTask(taskName.substring(Commands.todo.toString().length() + 1));
        } else if (taskName.startsWith(Commands.deadline.toString())){
            if (taskName.length() == Commands.deadline.toString().length()) {
                throw new InvalidParameterException("A Deadline task must have a task name.\nPlease try again");
            }
            addedTask = new DeadlineTask(taskName.substring(Commands.deadline.toString().length() + 1));
        } else if (taskName.startsWith(Commands.event.toString())){
            if (taskName.length() == Commands.event.toString().length()) {
                throw new InvalidParameterException("An Event task must have a task name.\nPlease try again");
            }
            addedTask = new EventTask(taskName.substring(Commands.event.toString().length() + 1));
        } else {
            throw new InvalidCommandExcpetion("Could not recognise the command, please try again.");
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
        if (inputMessage.equals(Commands.list.toString())) {
            Style.printStylised(list());
        } else if (inputMessage.startsWith(Commands.mark.toString())) {
            Style.printStylised(mark(Integer.parseInt(inputMessage.substring(Commands.mark.toString().length() + 1))));
        } else if (inputMessage.startsWith(Commands.unmark.toString())) {
            Style.printStylised(unmark(Integer.parseInt(inputMessage.substring(Commands.unmark.toString().length() + 1))));
        } else {
            try {
                Style.printStylised(add(inputMessage));
            } catch (Exception e) {
                Style.printStylised(e.getMessage());
            }
        }
    }
}
