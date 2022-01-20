package tasks;

public class EventTask extends Task{

    private final String eventTime;

    public EventTask(String taskName) {
        super(taskName, "E");
        this.eventTime = taskName.substring(taskName.lastIndexOf('\\') + 4).trim();
    }

    @Override
    public String toString(){
        return String.format("%s (at: %s)", super.toString(), eventTime);
    }

}
