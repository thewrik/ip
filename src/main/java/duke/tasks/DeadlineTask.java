package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate deadline;

    private DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName, "D");
        this.deadline = deadline;
    }

    private DeadlineTask(String taskName, LocalDate deadline, Boolean isTaskDone) {
        super(taskName, "D", isTaskDone);
        this.deadline = deadline;
    }

    /**
     * Factory method to generate Deadline task.
     *
     * @param taskName The name of the task to be created inclusive of the time parameter.
     *
     * @return the DeadlineTask created.
     * @throws InvalidParameterException If no name is passed, or an invalid date is passed.
     */
    public static DeadlineTask buildDeadlineTask(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.deadline.toString().length()) {
            throw new InvalidParameterException("A Deadline Task must have a task name.\nPlease try again!");
        }
        int lastIndexOfBackslash = taskName.lastIndexOf('/');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("A Deadline Task must have an associated deadline.\nPlease try again!");
        }

        return new DeadlineTask(
                taskName.substring(Commands.deadline.toString().length() + 1, lastIndexOfBackslash).trim(),
                LocalDate.parse(
                        taskName.substring(lastIndexOfBackslash + 4),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public static DeadlineTask generateFromString(String savedTask) {
        Boolean isTaskDone = savedTask.charAt(7)=='X';
        int indexOfTimeStamp = savedTask.lastIndexOf("(by: ");
        String taskName = savedTask.substring(10, indexOfTimeStamp).trim();
        LocalDate deadline = LocalDate.parse(savedTask.substring(indexOfTimeStamp + 5, savedTask.length() - 1),
            DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        return new DeadlineTask(taskName, deadline, isTaskDone);
    }

    /**
     * Generates the string representation of the Task.
     *
     * @return the string representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
    }

}
