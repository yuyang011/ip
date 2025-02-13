package ares.exception;

/**
 * Represents an exception specific to Ares.
 * This exception is thrown when the user inputs an index that is out of bound.
 */
public class OutOfBoundException extends AresException {
    /**
     * Constructs a new OutOfBoundException with the specified message.
     *
     * @param message The message describing the error that has occurred.
     */
    public OutOfBoundException(String message) {
        super("invalid number entered!!!");
    }
}