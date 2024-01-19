package Lists.OrderedLists;

import Interfaces.OrderedListADT;
import Lists.DoubleLinkedList.DoubleLinkedList;
import Lists.DoubleLinkedList.DoubleLinkedListNode;
import exceptions.NonComparableElementException;

/**
 * Represents an ordered list implemented using a doubly linked list.
 *
 * @param <T> the type of elements stored in the list, must extend Comparable
 */
public class DoubleLinkedOrderedList<T extends Comparable> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    /**
     * Adds the specified element to the ordered list in a sorted order.
     *
     * @param element the element to be added to the ordered list
     * @throws NonComparableElementException if the element or its class is not comparable
     */
    @Override
    public void add(T element) throws NonComparableElementException {
        Comparable<T> temp = (Comparable<T>) element;

        DoubleLinkedListNode<T> traverse = head;
        DoubleLinkedListNode<T> newnode = new DoubleLinkedListNode<T>(element);

        // If the list is empty, set the new node as both head and tail
        if (isEmpty()) {
            head = newnode;
            tail = newnode;
        } else if (temp.compareTo(tail.getElement()) >= 0) {
            // If the element is greater than or equal to the tail, add it to the end
            tail.setNext(newnode);
            newnode.setPrevious(tail);
            newnode.setNext(null);
            tail = newnode;
        } else if (temp.compareTo(head.getElement()) <= 0) {
            // If the element is less than or equal to the head, add it to the beginning
            head.setPrevious(newnode);
            newnode.setNext(head);
            newnode.setPrevious(null);
            head = newnode;
        } else {
            // Traverse the list to find the correct position to insert the element
            while ((temp.compareTo(traverse.getElement()) > 0)) {
                traverse = traverse.getNext();
            }

            // Insert the element at the determined position
            newnode.setNext(traverse);
            newnode.setPrevious(traverse.getPrevious());
            traverse.getPrevious().setNext(newnode);
            traverse.setPrevious(newnode);
        }

        count++;
    }
}
