package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.invalidCommandException;
import duke.exceptions.InvalidParameterException;


public class TaskManager {

    /**
     * @param taskName
     * @return
     * @throws invalidCommandException
     * @throws InvalidParameterException
     */
    public static String generateAndAdd(String taskName) throws invalidCommandException, InvalidParameterException {
        Task addedTask;
        taskName = taskName.trim();

        if (taskName.startsWith(Commands.todo.toString())) {
            addedTask = ToDoTask.ToDoTaskBuilder(taskName);
            assert(addedTask.toString().startsWith("[T]"));
        } else if (taskName.startsWith(Commands.deadline.toString())) {
            addedTask = DeadlineTask.DeadlineTaskBuilder(taskName);
            assert(addedTask.toString().startsWith("[D]"));
            assert(addedTask.toString().contains("(by:"));
        } else if (taskName.startsWith(Commands.event.toString())) {
            addedTask = EventTask.EventTaskBuilder(taskName);
            assert(addedTask.toString().startsWith("[E]"));
            assert(addedTask.toString().contains("(at:"));
        } else {
            throw new invalidCommandException("Could not recognise the command, please try again!");
        }

        return TaskList.add(addedTask);
    }


    public static String list() {
        return TaskList.list();
    }

    public static String mark(int index) {
        Task targetTask = TaskList.get(index);
        targetTask.mark();
        assert(targetTask.toString().startsWith("[X]", 3));
        return ("Well done! I have marked this task as done.\n" + targetTask.toString()).trim();
    }

    public static String unmark(int index) {
        Task targetTask = TaskList.get(index);
        targetTask.unmark();
        assert(targetTask.toString().startsWith("[ ]", 3));
        return ("No worries, I have unmarked this task, good luck!\n" + targetTask.toString()).trim();
    }

    public static String delete(int index) {
        Task deletedTask = TaskList.delete(index);
        return String.format("Noted. I have removed the following task:\n%s\nYou now have %s", deletedTask.toString().trim(), TaskList.countTasks());
    }

    public static void save() {

        TaskList.save();
    }
    
    public static void reinitialise() {
        TaskList.reinitialise();
    }

    public static String find(String taskKeyword) {
        return TaskList.find(taskKeyword);
    }

    public static String findBetter(String taskKeyword) {
        return TaskList.findBetter(taskKeyword);
    }

}
