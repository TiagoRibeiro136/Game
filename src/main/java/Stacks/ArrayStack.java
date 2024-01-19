package Stacks;

import Interfaces.StackADT;
import exceptions.EmptyCollectionException;

/**
 * ArrayStack represents a stack implemented using an array with dynamic capacity adjustment.
 *
 * @param <T> the type of elements stored in the stack
 */
public class ArrayStack<T> implements StackADT<T> {

    /**
     * Constant to represent the default capacity of the array.
     */
    private final int DEFAULT_CAPACITY = 100;
    /**
     * Integer that represents both the number of elements and the next
     * available position in the array.
     */
    private int top;
    /**
     * Array of generic elements to represent the stack.
     */
    private T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using the specified capacity.
     *
     * @param initialCapacity represents the specified capacity
     */
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Adds the specified element to the top of this stack,
     * expanding the capacity of the stack array if necessary.
     *
     * @param element generic element to be pushed onto the stack
     */
    public void push(T element) {
        if (size() == stack.length)
            expandCapacity();
        stack[top] = element;
        top++;
    }

    /**
     * Expands the capacity of the stack array.
     */
    public void expandCapacity() {
        int newSize = top + 1;
        T[] temp = (T[]) (new Object[newSize]);
        System.arraycopy(stack, 0, temp, 0, top);
        stack = temp;
    }

    /**
     * Removes the element at the top of this stack and
     * returns a reference to it.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element removed from the top of the stack
     * @throws EmptyCollectionException if a pop is attempted on an empty stack
     */
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Stack");
        top--;
        T result = stack[top];
        stack[top] = null;
        return result;
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element on top of the stack
     * @throws EmptyCollectionException if a peek is attempted on an empty stack
     */
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Stack");
        return stack[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Stacks.ArrayStack:\n");
        for (int i = 0; i < top; i++) {
            s.append(stack[i]).append("\n");
        }
        return s.toString();
    }
}
