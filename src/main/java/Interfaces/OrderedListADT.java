package Interfaces;

import exceptions.*;

/**
 * The {@code OrderedListADT} interface defines the common operations for an ordered list.
 *
 * @param <T> the type of elements stored in the ordered list
 */
public interface OrderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specified element to this ordered list at the proper location.
     *
     * @param element the element to be added to this list
     * @throws NonComparableElementException if the element is not comparable
     */
    public void add(T element) throws NonComparableElementException;
}
