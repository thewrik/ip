package tasks;

public class Task {
    private String taskName;
    private Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = Boolean.FALSE;
    }

    public void mark() {
        this.isDone = Boolean.TRUE;
    }

    public void unmark() {
        this.isDone = Boolean.FALSE;
    }

    public String toString() {
        String isDoneCharacter = isDone ? "X" : " ";
        return String.format("[%s] %s", isDoneCharacter, taskName);
    }
}
