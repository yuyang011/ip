package ares.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a specific deadline date and time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline task with a description and deadline date and time.
     *
     * @param description The description of the task to add.
     * @param by The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline task into a format suitable for file storage.
     *
     * @return A string representing the Deadline task into the file format.
     */
    @Override
    public String toFile() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D" + super.toFile() + " | " + by.format(outputFormat);
    }

    /**
     * Returns a string representing the Deadline task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}