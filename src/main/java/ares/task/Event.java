package ares.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a specific start and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event task with a description and a specific start and end time.
     *
     * @param description The description of the task to add.
     * @param from The Event start date and time.
     * @param to The Event end date and time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task into a format suitable for file storage.
     *
     * @return A string representing the Event task into the file format.
     */
    @Override
    public String toFile() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E" + super.toFile() + " | " + from.format(outputFormat) + " - " + to.format(outputFormat);
    }

    /**
     * Checks if the task is scheduled on the given date.
     *
     * @param date The date to check.
     * @return True if the task is scheduled on the date, false otherwise.
     */
    @Override
    public boolean isScheduledOn(LocalDate date) {
        return from.toLocalDate().equals(date) || to.toLocalDate().equals(date);
    }

    /**
     * Returns a string representing the Event task.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + " (from: " + this.from.format(outputFormat)
                + " to: " + this.to.format(outputFormat) + ")";
    }
}