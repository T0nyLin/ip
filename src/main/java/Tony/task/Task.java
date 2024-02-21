package Tony.task;

public class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        isDone = true;
    }
    public void markNotDone(){
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "+ description;
    }
}
