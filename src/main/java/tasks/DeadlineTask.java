package tasks;

import taskManagerExceptions.InvalidParameterException;

public class DeadlineTask extends Task{
    private final String deadline;

    public DeadlineTask(String taskName) throws InvalidParameterException {
        super(taskName, "D");
        int lastIndexOfBackslash = taskName.lastIndexOf('\\');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("A Deadline task must have an associated deadline.\nPlease try again");
        }
        this.deadline = taskName.substring(lastIndexOfBackslash + 3);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline);
    }

}
