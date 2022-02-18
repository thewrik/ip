package duke.tasks;

public class Task {
    private final String taskName;
    private Boolean isDone;
    private final String taskType;

    /**
     * Constructor for a Task
     *
     * @param taskName The name of the task.
     * @param taskType The type of the task.
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.isDone = Boolean.FALSE;
        this.taskType = taskType;
    }

    /**
     * Marks a task as completed.
     */
    public void mark() {
        this.isDone = Boolean.TRUE;
    }

    /**
     * Marks a task as yet to be completed.
     */
    public void unmark() {
        this.isDone = Boolean.FALSE;
    }

    /**
     * Generates the string representation of the Task.
     *
     * @return the string representation of the Task.
     */
    public String toString() {
        String isDoneCharacter = isDone ? "X" : " ";
        return String.format("[%s][%s] %s", taskType, isDoneCharacter, taskName);
    }
}
