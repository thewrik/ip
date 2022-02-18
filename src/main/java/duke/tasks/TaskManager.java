package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidParameterException;


public class TaskManager {

    /**
     * Generates a Task of the associated type and adds it to the TaskList.
     *
     * @param taskName The body of the task to be added.
     *
     * @return The response relayed by the TaskList acknowledging the addition.
     *
     * @throws InvalidCommandException If the user fails to prefix their task body with one of the three supported task types.
     * @throws InvalidParameterException If the user fails to satisfy the parameter requirements of the task type they used.
     */
    public static String generateAndAdd(String taskName) throws InvalidCommandException, InvalidParameterException {
        Task addedTask;
        taskName = taskName.trim();

        if (taskName.startsWith(Commands.todo.toString())) {
            addedTask = ToDoTask.buildTodoTask(taskName);
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
            throw new InvalidCommandException("Could not recognise the command, please try again!");
        }

        return TaskList.add(addedTask);
    }

    /**
     *  Returns a string representation of the list of tasks.
     */
    public static String list() {
        return TaskList.list();
    }

    /**
     * Marks a Task at a specific index (1-indexed) to be complete.
     *
     * @param index The integer passed to get the task at that index.
     * @return A message acknowledging the mark action with the corresponding task included.
     */
    public static String mark(int index) {
        Task targetTask = TaskList.get(index);
        targetTask.mark();
        assert(targetTask.toString().startsWith("[X]", 3));
        return ("Well done! I have marked this task as done.\n" + targetTask.toString()).trim();
    }

    /**
     * Unmarks a Task at a specific index (1-indexed), i.e., records it as yet to be completed.
     *
     * @param index The integer passed to get the task at that index.
     * @return A message acknowledging the unmark action with the corresponding task included.
     */
    public static String unmark(int index) {
        Task targetTask = TaskList.get(index);
        targetTask.unmark();
        assert(targetTask.toString().startsWith("[ ]", 3));
        return ("No worries, I have unmarked this task, good luck!\n" + targetTask.toString()).trim();
    }

    /**
     * Deletes the Task at a certain index (1-indexed list).
     *
     * @param index The integer passed to get the task at that index.
     * @return A message acknowledging the deletion with the deleted task included.
     */
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

    /**
     * This is a method that finds all the tasks in the task list which contain the keyword
     * in the task name as a constituent word.
     *
     * @param taskKeyword  The keyword being searched for.
     * @return A string containing the tasks found.
     */
    public static String find(String taskKeyword) {
        return TaskList.find(taskKeyword);
    }

    /**
     * This is a method that finds all the tasks in the task list which contain the keyword
     * in the task name as a constituent word, or as a substring of a constituent word.
     *
     * @param taskKeyword  The keyword being searched for.
     * @return A string containing the tasks found.
     */
    public static String findBetter(String taskKeyword) {
        return TaskList.findBetter(taskKeyword);
    }

}
