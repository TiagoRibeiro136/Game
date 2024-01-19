package Queues;

import Interfaces.QueueADT;
import exceptions.EmptyCollectionException;

/**
 * CircularArrayQueue represents a circular array implementation of a queue.
 *
 * @param <T> the type of elements stored in the queue
 */
public class CircularArrayQueue<T> implements QueueADT<T> {
    private final int SIZE = 100;
    private T[] queue;
    private int currentSize;
    private int front, rear;

    /**
     * Constructs an empty circular array queue.
     */
    public CircularArrayQueue() {
        this.currentSize = 0;
        this.front = this.rear = -1;
        this.queue = (T[]) (new Object[SIZE]);
    }

    /**
     * Adds the specified element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        if (this.currentSize == this.queue.length) {
            expandCapacity();
        } else {
            if (this.rear == this.front && this.front == -1) {
                this.front = 0;
            }
            this.rear = (rear + 1) % SIZE;
            this.queue[rear] = element;
            this.currentSize++;
        }
    }

    /**
     * Expands the capacity of the queue when necessary.
     */
    private void expandCapacity() {
        int tam = SIZE + 1;
        T[] temp = (T[]) (new Object[tam]);
        for (int i = 0; i < this.currentSize; i++) {
            temp[i] = this.queue[i];
        }
        this.queue = temp;
    }

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        T result = null;
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        if (this.front == queue.length - 1) {
            this.front = 0;
        } else {
            result = queue[front];
            if (this.front == this.rear) {
                this.front = this.rear = -1;
            } else {
                front = (front + 1) % SIZE;
                this.currentSize--;
            }
        }
        return result;
    }

    /**
     * Returns the element at the front of this queue without removing it.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        return queue[front];
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (this.front == -1);
    }

    /**
     * Returns the current size of the queue.
     *
     * @return the current size of the queue
     */
    @Override
    public int size() {
        return this.currentSize;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < currentSize; i++) {
            s.append(queue[i]).append(" | ");
        }
        return s.toString();
    }
}
