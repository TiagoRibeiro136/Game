package Interfaces;

import exceptions.*;

/**
 * The {@code UnorderedListADT} interface defines the common operations for an unordered list.
 *
 * @param <T> the type of elements stored in the unordered list
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to the front of the list.
     *
     * @param element the element to be added to the front of this list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to the rear of the list.
     *
     * @param element the element to be added to the rear of this list
     */
    public void addToRear(T element);

    /**
     * Adds the specified element after a particular element already in the list.
     *
     * @param element the element to be added to this list
     * @param target the target element already in the list
     * @throws EmptyCollectionException if the list is empty
     */
    public void addAfter(T element, T target) throws EmptyCollectionException;
}
