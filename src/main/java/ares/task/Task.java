package ares.task;

import java.time.LocalDate;

/**
 * Represents a Task with a specific description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Event task with a description and a specific start and end time.
     *
     * @param description The description of the task to add.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task in X for done or an empty space for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the status of the task, true for done and false for not done.
     */
    public boolean status() {
        return this.isDone;
    }

    /**
     * Converts the Task into a format suitable for file storage.
     *
     * @return A string representing the Task into the file format.
     */
    public String toFile() {
        return " | " + (status() ? "1" : "0") + " | " + this.description ;
    }

    /**
     * Returns a string representing the description of the Task.
     *
     * @return A string representing the description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is scheduled on the given date.
     *
     * @param date The date to check.
     * @return True if the task is scheduled on the date, false otherwise.
     */
    public boolean isScheduledOn(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representing the Task.
     *
     * @return A string representing the Task.
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "]" + " " + this.description);
    }
}