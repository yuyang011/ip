package ares.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ares.exception.AresException;
import ares.exception.OutOfBoundException;
import ares.command.AddCommand;
import ares.command.MarkCommand;
import ares.command.Command;
import ares.command.ExitCommand;
import ares.command.UnmarkCommand;
import ares.command.ListCommand;
import ares.command.DeleteCommand;
import ares.task.Todo;
import ares.task.Event;
import ares.task.Deadline;

/**
 * Represents a class that parses user input and converts it into executable commands.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param fullCommand The raw user input string.
     * @return A command representing the user's action.
     * @throws AresException If the command is invalid or improperly formatted.
     */
    public static Command parse(String fullCommand) throws AresException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskNumber(arguments));
        case "unmark":
            return new UnmarkCommand(parseTaskNumber(arguments));
        case "todo":
            Todo todo = new Todo(parts[1]);
            return new AddCommand(todo);
        case "deadline":
            String[] subParts = parts[1].split(" /by ");
            LocalDateTime by = parseLocalDateTime(subParts[1]);
            Deadline deadline = new Deadline(subParts[0], by);
            return new AddCommand(deadline);
        case "event":
            String[] eventParts = parts[1].split(" /from ");
            String[] eventSubParts = eventParts[1].split(" /to ");
            LocalDateTime from = parseLocalDateTime(eventSubParts[0]);
            LocalDateTime to = parseLocalDateTime(eventSubParts[1]);
            Event event = new Event(eventParts[0], from, to);
            return new AddCommand(event);
        case "delete":
            return new DeleteCommand(parseTaskNumber(arguments));
        default:
            throw new AresException("I do not understand what you entered");
        }
    }

    /**
     * Parses the given user input and returns the correct integer value.
     *
     * @param arguments The raw user input string.
     * @return An integer that representing the taskNumber the user specified.
     * @throws OutOfBoundException value provided by the user is invalid.
     */
    private static int parseTaskNumber(String arguments) throws OutOfBoundException {
        try {
            int taskNum = Integer.parseInt(arguments) - 1;
            if (taskNum < 0) {
                throw new OutOfBoundException("You have entered an invalid number");
            }
            return taskNum;
        } catch (NumberFormatException e) {
            throw new OutOfBoundException("You have entered an invalid number");
        }
    }

    /**
     * Converts the given user input time and returns the correct format that is to be used.
     *
     * @param dateTime The raw user input string.
     * @return A LocalDateTime object that represents the time the user specified.
     * @throws OutOfBoundException If the value provided by the user is not in the correct format.
     */
    public static LocalDateTime parseLocalDateTime(String dateTime) throws AresException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AresException("The correct time format is YYYY-MM-DD HHmm");
        }
    }
}