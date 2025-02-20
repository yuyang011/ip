package ares.task; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.of(2022, 06, 22, 18, 00);
        Deadline deadline = new Deadline("Do workout", by);
        String expectedOutput = "[D][ ] Do workout (by: 2022-06-22 1800)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testToFile() {
        LocalDateTime by = LocalDateTime.of(2022, 06, 22, 18, 00);
        Deadline deadline = new Deadline("Do workout", by);
        String expectedOutput = "D | 0 | Do workout | 2022-06-22 1800";
        assertEquals(expectedOutput, deadline.toFile());
    }
}
