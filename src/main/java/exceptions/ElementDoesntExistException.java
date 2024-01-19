package exceptions;

/**
 * The {@code ElementDoesntExistException} class is an exception that indicates
 * that an attempt to access or manipulate an element that doesn't exist has occurred.
 * This exception is typically thrown when an operation expects an element to be present,
 * but the element is not found.
 *
 * @author Gil Leão
 * @version 1.0
 * @since 2023-11-30
 */
public class ElementDoesntExistException extends Exception {

    /**
     * Constructs a new {@code ElementDoesntExistException} with no specified detail message.
     */
    public ElementDoesntExistException() {
        super();
    }

    /**
     * Constructs a new {@code ElementDoesntExistException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public ElementDoesntExistException(String message) {
        super(message);
    }
}
