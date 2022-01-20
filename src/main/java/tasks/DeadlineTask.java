package tasks;

import taskManagerExceptions.InvalidParameterException;

public class DeadlineTask extends Task{
    private final String deadline;

    private DeadlineTask(String taskName, String deadline) {
        super(taskName, "D");
        this.deadline = deadline;
    }
    public static DeadlineTask DeadlineTaskBuilder(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.deadline.toString().length()) {
            throw new InvalidParameterException("A Deadline Task must have a task name.\nPlease try again!");
        }
        int lastIndexOfBackslash = taskName.lastIndexOf('/');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("A Deadline Task must have an associated deadline.\nPlease try again!");
        }
        return new DeadlineTask(taskName.substring(Commands.deadline.toString().length() + 1, lastIndexOfBackslash).trim(), taskName.substring(lastIndexOfBackslash + 4));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline);
    }

}
