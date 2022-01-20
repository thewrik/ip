package tasks;

public class DeadlineTask extends Task{
    private final String deadline;

    public DeadlineTask(String taskName) {
        super(taskName, "D");
        this.deadline = taskName.substring(taskName.lastIndexOf('/') + 3);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline);
    }

}
