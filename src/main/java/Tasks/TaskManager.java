package Tasks;

import Storage.Storage;
import StyleAuxillaries.Style;
import TaskManagerExceptions.InvalidCommandExcpetion;
import TaskManagerExceptions.InvalidParameterException;

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
            addedTask = ToDoTask.ToDoTaskBuilder(taskName);
        } else if (taskName.startsWith(Commands.deadline.toString())){
            addedTask = DeadlineTask.DeadlineTaskBuilder(taskName);
        } else if (taskName.startsWith(Commands.event.toString())) {
            addedTask = EventTask.EventTaskBuilder(taskName);
        } else {
            throw new InvalidCommandExcpetion("Could not recognise the command, please try again!");
        }

        taskList.add(addedTask);
        taskCounter++;

        return String.format("Great, have added the following task for you:\n%s\nNow you have %s.", addedTask, countTasks());
    }

    private static String list() {
        int task_no = 1;
        StringBuilder prompt = new StringBuilder("");

        prompt.append(String.format("You have %s in the list\n", countTasks()));

        for (Task t:taskList) {
            prompt.append(String.format("%d. %s\n", task_no++, t.toString()));
        }
        return prompt.toString().trim();
    }

    private static String mark(int index) {
        Task targetTask = taskList.get(index - 1);
        targetTask.mark();
        return ("Well done! I have marked this task as done.\n" + targetTask.toString()).trim();
    }

    private static String unmark(int index) {
        Task targetTask = taskList.get(index - 1);
        targetTask.unmark();
        return ("No worries, I have unmarked this task, good luck!\n" + targetTask.toString()).trim();
    }

    private static String delete(int index) {
        Task deletedTask = taskList.remove(index - 1);
        taskCounter--;
        return String.format("Noted. I have removed the following task:\n%s\nYou now have %s",deletedTask.toString().trim(), countTasks());
    }

    private static String save() {
        int task_no = 1;
        StringBuilder currentListState = new StringBuilder("");

        for (Task t:taskList) {
            currentListState.append(String.format("%d. %s\n", task_no++, t.toString()));
        }

        return Storage.save(currentListState.toString());
    }


    public static void process(String inputMessage) {
        if (inputMessage.trim().equals(Commands.list.toString())) {
            Style.printStylised(list());
        } else if (inputMessage.startsWith(Commands.mark.toString())) {
            Style.printStylised(mark(Integer.parseInt(inputMessage.substring(Commands.mark.toString().length() + 1))));
            save();
        } else if (inputMessage.startsWith(Commands.unmark.toString())) {
            Style.printStylised(unmark(Integer.parseInt(inputMessage.substring(Commands.unmark.toString().length() + 1))));
            save();
        } else if (inputMessage.startsWith(Commands.delete.toString())) {
            Style.printStylised(delete(Integer.parseInt(inputMessage.substring(Commands.delete.toString().length() + 1))));
            save();
        } else if (inputMessage.startsWith(Commands.save.toString())) {
            Style.printStylised(save());
        } else {
            try {
                Style.printStylised(add(inputMessage));
                save();
            } catch (Exception e) {
                Style.printStylised(e.getMessage());
            }
        }
    }
}
