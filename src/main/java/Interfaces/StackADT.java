package Interfaces;

import exceptions.*;

/**
 * The {@code StackADT} interface defines the common operations for a stack.
 *
 * @param <T> the type of elements stored in the stack
 */
public interface StackADT<T> {

    /**
     * Adds one element to the top of this stack.
     *
     * @param element the element to be pushed onto the stack
     */
    public void push(T element);

    /**
     * Removes and returns the top element from this stack.
     *
     * @return the element removed from the top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    public T pop() throws EmptyCollectionException;

    /**
     * Returns without removing the top element of this stack.
     *
     * @return the element on top of the stack
     * @throws EmptyCollectionException if the stack is empty
     */
    public T peek() throws EmptyCollectionException;

    /**
     * Returns true if this stack contains no elements.
     *
     * @return true if this stack is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this stack.
     *
     * @return the integer representation of the number of elements in this stack
     */
    public int size();

    /**
     * Returns a string representation of this stack.
     *
     * @return the string representation of this stack
     */
    @Override
    public String toString();
}
