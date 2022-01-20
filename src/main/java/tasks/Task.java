package tasks;

public class Task {
    private String taskName;
    private Boolean isDone;
    private String taskType;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = Boolean.FALSE;
        this.taskType = " ";
    }
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.isDone = Boolean.FALSE;
        this.taskType = taskType;
    }

    public void mark() {
        this.isDone = Boolean.TRUE;
    }

    public void unmark() {
        this.isDone = Boolean.FALSE;
    }

    public String toString() {
        String isDoneCharacter = isDone ? "X" : " ";
        return String.format("[%s][%s] %s", taskType, isDoneCharacter, taskName);
    }
}
