package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidParameterException;

public class ToDoTask extends Task {
    private ToDoTask(String taskName) {
        super(taskName, "T");
    }

    /**
     * Factory method to generate TODO task.
     *
     * @param taskName The name of the task to be created.
     *
     * @return the ToDoTask created.
     * @throws InvalidParameterException If no name is passed.
     */
    public static ToDoTask buildTodoTask(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.todo.toString().length()) {
            throw new InvalidParameterException("A Todo Task must have a task name.\nPlease try again!");
        }
        return new ToDoTask(taskName.substring(Commands.todo.toString().length() + 1).trim());
    }

}
