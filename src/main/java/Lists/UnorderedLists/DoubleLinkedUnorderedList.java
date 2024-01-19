package Lists.UnorderedLists;

import Interfaces.UnorderedListADT;
import Lists.DoubleLinkedList.DoubleLinkedList;
import Lists.DoubleLinkedList.DoubleLinkedListNode;
import exceptions.EmptyCollectionException;

/**
 * Represents an unordered doubly linked list.
 *
 * @param <T> the type of elements stored in the list
 */
public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    /**
     * Constructs an empty doubly linked unordered list.
     */
    public DoubleLinkedUnorderedList() {
        super();
    }

    /**
     * Adds the specified element to the front of the doubly linked unordered list.
     *
     * @param element the element to be added to the front of the list
     */
    @Override
    public void addToFront(T element) {
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element);

        // Set the element for the new node
        newNode.setElement(element);

        // If the list is empty, the new node becomes both the head and the tail
        if (head == null) {
            head = newNode;
            tail = newNode;
        }

        // Insert the new node at the front of the list
        newNode.setNext(head);
        head.setPrevious(newNode);
        head = newNode;
        count++;
    }

    /**
     * Adds the specified element to the rear of the doubly linked unordered list.
     *
     * @param element the element to be added to the rear of the list
     */
    @Override
    public void addToRear(T element) {
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element);

        // Set the element for the new node
        newNode.setElement(element);

        // If the list is empty, the new node becomes both the head and the tail
        if (head == null) {
            newNode.setPrevious(null);
            head = newNode;
            tail = newNode;
        }

        // Insert the new node at the rear of the list
        newNode.setPrevious(tail);
        tail.setNext(newNode);
        tail = newNode;
        count++;
    }

    /**
     * Adds the specified element after the target element in the doubly linked unordered list.
     *
     * @param element the element to be added after the target element
     * @param target  the target element after which the new element is added
     * @throws EmptyCollectionException if the list is empty
     */
    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException {
        DoubleLinkedListNode<T> newnode = new DoubleLinkedListNode<T>(element);

        // Set the element for the new node
        newnode.setElement(element);

        boolean found = false;

        // If the list is empty, the new node becomes both the head and the tail
        if (isEmpty()) {
            head = newnode;
            tail = newnode;
        } else {
            DoubleLinkedListNode<T> current = head;

            // Search for the target element in the list
            while (current != null && !found) {
                if (target.equals(current.getElement())) {
                    found = true;
                } else {
                    current = current.getNext();
                }
            }

            // If the target element is not found, add the new node at the end of the list
            if (current.getNext() == null) {
                current.setNext(newnode);
                newnode.setPrevious(current);
                tail = newnode;
            } else {
                // Otherwise, insert the new node after the target element
                DoubleLinkedListNode<T> next = current.getNext();
                current.setNext(newnode);
                newnode.setPrevious(current);
                newnode.setNext(next);
                next.setPrevious(newnode);
            }
        }
        count++;
    }
}
