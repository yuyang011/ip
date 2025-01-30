public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean status() {
        return this.isDone;
    }

    public String toFile() {
        return " | " + (status() ? "1" : "0") + " | " + this.description ;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "]" + " " + this.description);
    }
}