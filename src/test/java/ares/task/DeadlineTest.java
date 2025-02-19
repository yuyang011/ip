package ares.task; //same package as the class being tested
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.of(2022, 06, 22, 18, 00);
        Deadline deadline = new Deadline("Do workout", by);
        String expectedOutput = "[D][ ] Do workout (by: Jun 22 2022 1800)";
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