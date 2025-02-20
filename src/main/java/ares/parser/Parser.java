package ares.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ares.command.AddCommand;
import ares.command.Command;
import ares.command.DeleteCommand;
import ares.command.ExitCommand;
import ares.command.FindCommand;
import ares.command.ListCommand;
import ares.command.MarkCommand;
import ares.command.UnmarkCommand;
import ares.command.ViewCommand;
import ares.exception.AresException;
import ares.exception.OutOfBoundException;
import ares.task.Deadline;
import ares.task.Event;
import ares.task.Todo;

/**
 * Represents a class that parses user input and converts it into executable commands.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param fullCommand The raw user input string.
     * @return A command representing the user's action.
     * @throws AresException If the command is invalid or improperly formatted.
     */
    public static Command parse(String fullCommand) throws AresException {
        assert fullCommand != null && !fullCommand.isEmpty() : "User input cannot be null or empty!";
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            checkDescription(parts);
            return new MarkCommand(parseTaskNumber(arguments));
        case "unmark":
            checkDescription(parts);
            return new UnmarkCommand(parseTaskNumber(arguments));
        case "todo":
            checkDescription(parts);
            Todo todo = new Todo(parts[1]);
            return new AddCommand(todo);
        case "deadline":
            checkDescription(parts);
            String[] subParts = parts[1].split(" /by ");
            checkDeadline(subParts);
            LocalDateTime by = parseLocalDateTime(subParts[1]);
            Deadline deadline = new Deadline(subParts[0], by);
            return new AddCommand(deadline);
        case "event":
            checkDescription(parts);
            String[] eventParts = parts[1].split(" /from ");
            checkEventFrom(eventParts);
            String[] eventSubParts = eventParts[1].split(" /to ");
            LocalDateTime from = parseLocalDateTime(eventSubParts[0]);
            checkEventTo(eventSubParts);
            LocalDateTime to = parseLocalDateTime(eventSubParts[1]);
            Event event = new Event(eventParts[0], from, to);
            return new AddCommand(event);
        case "delete":
            checkDescription(parts);
            return new DeleteCommand(parseTaskNumber(arguments));
        case "find":
            checkDescription(parts);
            return new FindCommand(arguments);
        case "view":
            checkDescription(parts);
            LocalDate date = parseLocalDate(arguments);
            return new ViewCommand(date);
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

    /**
     * Converts the given user input date and returns the correct format that is to be used.
     *
     * @param dateTime The raw user input string.
     * @return A LocalDate object that represents the time the user specified.
     * @throws OutOfBoundException If the value provided by the user is not in the correct format.
     */
    public static LocalDate parseLocalDate(String dateTime) throws AresException {
        try {
            return LocalDate.parse(dateTime, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AresException("The correct time format is YYYY-MM-DD");
        }
    }

    /**
     * Checks if the given input data is in a valid format.
     *
     * @param inputParts The raw user input string.
     * @throws AresException If the value provided by the user is not in the correct format or empty.
     */
    private static void checkDescription(String[] inputParts) throws AresException {
        if (inputParts.length == 1 || inputParts[1].trim().isEmpty()) {
            throw new AresException("Invalid description entered!!!");
        }
    }

    /**
     * Checks if the given input data after deadline is in a valid format.
     *
     * @param inputParts The raw user input string.
     * @throws AresException If the value provided by the user is not in the correct format or empty.
     */
    private static void checkDeadline(String[] inputParts) throws AresException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AresException("Time and date must be followed after /by ");
        }
    }

    /**
     * Checks if the given input data after event is in a valid format.
     *
     * @param inputParts The raw user input string.
     * @throws AresException If the value provided by the user is not in the correct format or empty.
     */
    private static void checkEventFrom(String[] inputParts) throws AresException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AresException("Time and date must be followed after /from ");
        }
    }

    /**
     * Checks if the given input data after event /from is in a valid format.
     *
     * @param inputParts The raw user input string.
     * @throws AresException If the value provided by the user is not in the correct format or empty.
     */
    private static void checkEventTo(String[] inputParts) throws AresException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AresException("Time and date must be followed after /to ");
        }
    }
}
