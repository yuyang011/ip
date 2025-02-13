package ares.exception;

/**
 * Represents an exception specific to Ares.
 * This exception is thrown when an error relating to user commands
 * or command related operations occurs.
 */
public class AresException extends Exception {
    /**
     * Constructs a new AresException with the specified message.
     *
     * @param message The message describing the error that has occurred.
     */
    public AresException(String message) {
        super(message);
    }
}