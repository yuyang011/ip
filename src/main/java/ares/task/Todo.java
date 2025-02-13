package ares.task;

/**
 * Represents a Todo task with a specific ending time.
 */
public class Todo extends Task {
    private String by;

    /**
     * Constructs a new Todo task with a description and a specific start and end time.
     *
     * @param description The description of the task to add.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task into a format suitable for file storage.
     *
     * @return A string representing the Todo task into the file format.
     */
    @Override
    public String toFile() {
        return "T" + super.toFile() ;
    }

    /**
     * Returns a string representing the Todo task.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}