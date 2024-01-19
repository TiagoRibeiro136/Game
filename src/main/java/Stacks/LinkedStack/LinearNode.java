package Stacks.LinkedStack;

/**
 * LinearNode represents a node in a linked structure.
 *
 * @param <T> the type of elements stored in the node
 */
public class LinearNode<T> {
    /** Reference to the next node in the linked structure */
    private LinearNode<T> next;
    /** Element stored at this node */
    private T element;

    /** Creates an empty node. */
    public LinearNode() {
        next = null;
        element = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param elem element to be stored
     */
    public LinearNode(T elem) {
        next = null;
        element = elem;
    }

    /**
     * Returns the node that follows this one.
     *
     * @return LinearNode<T> reference to the next node
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param node node to follow this one
     */
    public void setNext(LinearNode<T> node) {
        next = node;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return T element stored at this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param elem element to be stored at this node
     */
    public void setElement(T elem) {
        element = elem;
    }
}
