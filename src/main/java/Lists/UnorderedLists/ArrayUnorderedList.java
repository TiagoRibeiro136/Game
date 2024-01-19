package Lists.UnorderedLists;

import Interfaces.UnorderedListADT;
import Lists.ArrayLists.ArrayList;
import exceptions.EmptyCollectionException;

/**
 * Represents an unordered list implemented using an array.
 *
 * @param <T> the type of elements stored in the list
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Adds the specified element to the front of the unordered list.
     *
     * @param element the element to be added to the front of the list
     */
    @Override
    public void addToFront(T element) {
        T[] unorderedList = list;

        // If the array is full, expand its capacity
        if (rear == unorderedList.length - 1) {
            expandCapacity();
        }

        // Shift elements to make space for the new element at the front
        for (int i = rear; i > front; i--) {
            unorderedList[i] = unorderedList[i - 1];
        }

        // Add the element to the front
        unorderedList[front] = element;
        rear++;
        modCount++;
    }

    /**
     * Expands the capacity of the array when needed.
     */
    private void expandCapacity() {
        T[] unorderedList = list;
        int newCapacity = unorderedList.length + 1;
        T[] temp = (T[]) (new Object[newCapacity]);

        // Copy existing elements to the new array
        for (int i = 0; i < rear; i++) {
            temp[i] = unorderedList[i];
        }

        // Update the list reference to the new array
        unorderedList = temp;
    }

    /**
     * Adds the specified element to the rear of the unordered list.
     *
     * @param element the element to be added to the rear of the list
     */
    @Override
    public void addToRear(T element) {
        T[] unorderedList = list;

        // If the array is full, expand its capacity
        if (rear == unorderedList.length - 1) {
            expandCapacity();
        }

        // Add the element to the rear
        unorderedList[rear] = element;
        rear++;
        modCount++;
    }

    /**
     * Adds the specified element after the target element in the unordered list.
     *
     * @param element the element to be added after the target element
     * @param target  the target element after which the new element is added
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException {
        T[] unorderedList = list;

        // If the array is full, expand its capacity
        if (rear == unorderedList.length - 1) {
            expandCapacity();
        }

        int position = 0;

        // Find the position of the target element in the list
        for (int i = 0; i < rear; i++) {
            if (target.equals(unorderedList[i])) {
                position = i;
            }
            i++;
        }

        // Shift elements to make space for the new element after the target
        for (int j = rear; j > position; j--) {
            unorderedList[j] = unorderedList[j - 1];
        }

        // Add the element after the target
        unorderedList[position + 1] = element;
        rear++;
        modCount++;
    }
}
