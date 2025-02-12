package ares.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFile() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D" + super.toFile() + " | " + by.format(outputFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}