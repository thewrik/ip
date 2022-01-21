package Tasks;

import TaskManagerExceptions.InvalidParameterException;

public class EventTask extends Task{

    private final String eventTime;

    private EventTask(String taskName, String eventTime) {
        super(taskName, "E");
        this.eventTime = eventTime;
    }

    public static EventTask EventTaskBuilder(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.event.toString().length()) {
            throw new InvalidParameterException("An Event Task must have a task name.\nPlease try again!");
        }
        int lastIndexOfBackslash = taskName.lastIndexOf('/');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("An Event Task must have an associated event time.\nPlease try again!");
        }
        return new EventTask(taskName.substring(Commands.event.toString().length() + 1, lastIndexOfBackslash).trim(), taskName.substring(lastIndexOfBackslash + 4));
    }


    @Override
    public String toString(){
        return String.format("%s (at: %s)", super.toString(), eventTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
    }

}
