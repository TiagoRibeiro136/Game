package Lists.OrderedLists;

import Interfaces.OrderedListADT;
import Lists.ArrayLists.ArrayList;
import exceptions.NonComparableElementException;

/**
 * Represents an ordered list implemented using an array.
 *
 * @param <T> the type of elements stored in the list
 */
public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Constructs an empty ordered list.
     */
    public ArrayOrderedList() {
        super();
    }

    /**
     * Adds the specified element to the ordered list in a sorted order.
     *
     * @param element the element to be added to the ordered list
     * @throws NonComparableElementException if the element or its class is not comparable
     */
    @Override
    public void add(T element) throws NonComparableElementException {
        if (!(element instanceof Comparable))
            throw new NonComparableElementException("O elemento ou classe não é comparável");

        if (size() == list.length) {
            expandCapacity();
        }

        Comparable<T> temp = (Comparable<T>) element;
        int index = 0;

        // Find the position to insert the element in sorted order
        while (index < rear && temp.compareTo(list[index]) > 0) {
            index++;
        }

        // Shift elements to make space for the new element
        for (int j = this.rear; j > index; j--) {
            list[j] = list[j - 1];
        }

        // Insert the element at the determined position
        this.list[index] = element;
        rear++;
        modCount++;
    }

    /**
     * Expands the capacity of the array when needed.
     */
    private void expandCapacity() {
        int newCapacity = list.length + 1;
        T[] temp = (T[]) (new Object[newCapacity]);

        // Copy existing elements to the new array
        for (int i = 0; i < rear; i++) {
            temp[i] = list[i];
        }

        // Update the list reference to the new array
        list = temp;
    }
}
