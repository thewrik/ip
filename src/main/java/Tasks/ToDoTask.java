package Tasks;

import TaskManagerExceptions.InvalidParameterException;

public class ToDoTask extends Task{
    private ToDoTask(String taskName) {
        super(taskName, "T");
    }
    public static ToDoTask ToDoTaskBuilder(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.todo.toString().length()) {
            throw new InvalidParameterException("A Todo Task must have a task name.\nPlease try again!");
        }
        return new ToDoTask(taskName.substring(Commands.todo.toString().length()+1).trim());
    }

}
