package Queues.LinkedQueue;

import Interfaces.QueueADT;
import exceptions.EmptyCollectionException;

/**
 * LinkedQueue represents a linked implementation of a queue.
 *
 * @param <T> the type of elements in the queue
 */
public class LinkedQueue<T> implements QueueADT {

    private LinearNode<T> front, rear;

    private int count;

    /**
     * Constructs an empty queue.
     */
    public LinkedQueue(){
        this.count = count;
        this.front = front;
        this.rear = rear;
    };

    /**
     * Adds the specified element to the rear of the queue.
     *
     * @param element the element to be added to the rear of the queue
     */
    @Override
    public void enqueue(Object element) {
        LinearNode<T> node = new LinearNode<T>((T) element);

        if (front == null)
            front = rear = node;
        else {
            rear.setNext(node);
            rear = node;
        }
        this.count++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public Object dequeue() throws EmptyCollectionException {
        if(isEmpty())
            throw new EmptyCollectionException("");

        LinearNode<T> temp = this.front;

        this.front = this.front.getNext();

        if(front == null)
            this.rear = null;

        T item = temp.getElement();
        this.count--;
        return item;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     */
    @Override
    public Object first() {
        return (T)(front.getElement());
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        LinearNode<T> current = this.front;
        String s = "Queues.LinkedQueue.LinkedQueue.LinkedQueue:\n";
        while(current != null) {
            s += current.getElement().toString() + "\n";
            current = current.getNext();
        }
        return s;
    }
}
