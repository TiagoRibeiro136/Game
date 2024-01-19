package Stacks.LinkedStack;

import exceptions.NullException;

/**
 * LinkedStack represents a stack implemented using a linked structure.
 *
 * @param <T> the type of elements stored in the stack
 */
public class LinkedStack<T> {
    /** Number of elements in the stack */
    int count;
    /** Reference to the top node of the stack */
    LinearNode<T> top;

    /** Creates an empty stack. */
    public LinkedStack() {
        this.count = 0;
        this.top = null;
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        newNode.setElement(element);
        if (this.top == null) {
            this.top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
        this.count++;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NullException if the stack is empty
     */
    public T pop() throws NullException {
        if (isEmpty()) throw new NullException("Não existem elementos na lista ligada");
        T result = top.getElement();
        top = top.getNext();
        count--;
        return result;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws NullException if the stack is empty
     */
    public T peek() throws NullException {
        if (isEmpty()) throw new NullException("Stack");
        LinearNode<T> temp = this.top;
        return temp.getElement();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        LinearNode<T> current = top;
        String s = "LinkedList:\n";
        while (current != null) {
            s += current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return s;
    }
}
