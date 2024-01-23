package Lists.LinkedList;

import java.util.Objects;

/**
 * Represents a node in a singly linked list.
 *
 * @param <T> the type of element stored in the node
 */
public class LinkedListNode<T> {
    private LinkedListNode<T> next;
    private T element;

    /**
     * Constructs a linked list node with the specified element.
     *
     * @param element the element to be stored in the node
     */
    public LinkedListNode(T element) {
        this.element = element;
    }

    /**
     * Gets the next node in the linked list.
     *
     * @return the next node in the linked list
     */
    public LinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the linked list.
     *
     * @param next the next node in the linked list
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    /**
     * Gets the element stored in the node.
     *
     * @return the element stored in the node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element to be stored in the node.
     *
     * @param element the element to be stored in the node
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Compares this node with the specified object for equality.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LinkedListNode<?> that = (LinkedListNode<?>) obj;
        return Objects.equals(next, that.next) && Objects.equals(element, that.element);
    }
}
