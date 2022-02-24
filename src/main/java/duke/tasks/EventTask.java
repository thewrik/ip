package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private final LocalDate eventTime;

    private EventTask(String taskName, LocalDate eventTime) {
        super(taskName, "E");
        this.eventTime = eventTime;
    }

    private EventTask(String taskName, LocalDate eventTime, Boolean isDone) {
        super(taskName, "E", isDone);
        this.eventTime = eventTime;
    }


    /**
     * Factory method to generate Event task.
     *
     * @param taskName The name of the task to be created inclusive of the time parameter.
     *
     * @return the EventTask created.
     * @throws InvalidParameterException If no name is passed, or an invalid date is passed.
     */
    public static EventTask buildEventTask(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.event.toString().length()) {
            throw new InvalidParameterException("An Event Task must have a task name.\nPlease try again!");
        }
        int lastIndexOfBackslash = taskName.lastIndexOf('/');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("An Event Task must have an associated event time.\nPlease try again!");
        }
        return new EventTask(
            taskName.substring(Commands.event.toString().length() + 1, lastIndexOfBackslash).trim(),
            LocalDate.parse(
                taskName.substring(lastIndexOfBackslash + 4),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public static EventTask generateFromString(String savedTask) {
        Boolean isTaskDone = savedTask.charAt(7)=='X';
        int indexOfTimeStamp = savedTask.lastIndexOf("(at: ");
        String taskName = savedTask.substring(10, indexOfTimeStamp).trim();
        LocalDate eventTime = LocalDate.parse(savedTask.substring(indexOfTimeStamp + 5, savedTask.length() - 1),
            DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        return new EventTask(taskName, eventTime, isTaskDone);
    }

    /**
     * Generates the string representation of the Task.
     *
     * @return the string representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), eventTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
    }

}
