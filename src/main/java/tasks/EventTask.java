package tasks;

import taskManagerExceptions.InvalidParameterException;

public class EventTask extends Task{

    private final String eventTime;

    public EventTask(String taskName) throws InvalidParameterException {
        super(taskName, "E");
        int lastIndexOfBackslash = taskName.lastIndexOf('\\');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("An Event task must have an associated event time.\nPlease try again");
        }
        this.eventTime = taskName.substring(lastIndexOfBackslash + 4).trim();
    }

    @Override
    public String toString(){
        return String.format("%s (at: %s)", super.toString(), eventTime);
    }

}
