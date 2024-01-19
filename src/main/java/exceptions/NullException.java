package exceptions;

/**
 * The {@code NullException} class is an exception that indicates
 * an attempt to perform an operation on a null reference.
 * This exception is typically thrown when a method expects a non-null value
 * but receives a null reference, leading to an error.
 *
 * @author Gil Leão
 * @version 1.0
 * @since 2023-11-30
 */
public class NullException extends Exception {

    /**
     * Constructs a new {@code NullException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public NullException(String message) {
        super("Erro " + message);
    }
}
