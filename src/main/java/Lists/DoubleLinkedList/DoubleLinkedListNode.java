package Lists.DoubleLinkedList;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <T> the type of element stored in the node
 */
public class DoubleLinkedListNode<T> {
    private DoubleLinkedListNode<T> next;
    private T element;
    private DoubleLinkedListNode<T> previous;

    /**
     * Constructs an empty doubly linked list node.
     */
    public DoubleLinkedListNode() {
        this.next = null;
        this.element = null;
        this.previous = null;
    }

    /**
     * Constructs a doubly linked list node with the specified element.
     *
     * @param elem the element to be stored in the node
     */
    public DoubleLinkedListNode(T elem) {
        this.next = null;
        this.element = elem;
        this.previous = null;
    }

    /**
     * Gets the next node in the doubly linked list.
     *
     * @return the next node in the doubly linked list
     */
    public DoubleLinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the doubly linked list.
     *
     * @param dnode the next node in the doubly linked list
     */
    public void setNext(DoubleLinkedListNode<T> dnode) {
        this.next = dnode;
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
     * @param elem the element to be stored in the node
     */
    public void setElement(T elem) {
        this.element = elem;
    }

    /**
     * Gets the previous node in the doubly linked list.
     *
     * @return the previous node in the doubly linked list
     */
    public DoubleLinkedListNode<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the previous node in the doubly linked list.
     *
     * @param dnode the previous node in the doubly linked list
     */
    public void setPrevious(DoubleLinkedListNode<T> dnode) {
        this.previous = dnode;
    }

    /**
     * Returns a string representation of the node.
     *
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return "LinkedNode{" + "element=" + element + '}';
    }
}
