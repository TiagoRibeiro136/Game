package exceptions;

/**
 * The {@code EmptyCollectionException} class is an exception that indicates
 * an attempt to perform an operation on an empty collection.
 * This exception is typically thrown when a method expects the collection to contain elements,
 * but the collection is found to be empty, leading to an error.
 *
 * @author Gil Leão
 * @version 1.0
 * @since 2023-11-30
 */
public class EmptyCollectionException extends Exception {

    /**
     * Constructs a new {@code EmptyCollectionException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public EmptyCollectionException(String message) {
        super("Erro " + message);
    }
}
