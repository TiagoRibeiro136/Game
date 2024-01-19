package Lists.ArrayLists;

import Interfaces.ListADT;
import exceptions.EmptyCollectionException;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Abstract base class for implementing an ArrayList data structure.
 *
 * @param <T> the type of elements stored in the ArrayList
 */
public abstract class ArrayList<T> implements ListADT<T> {
    private final int SIZE = 100;
    protected T[] list;
    protected int front;
    protected int rear;
    protected int modCount;

    /**
     * Constructs an ArrayList with default capacity.
     */
    public ArrayList() {
        this.list = (T[]) (new Object[SIZE]);
        this.front = 0;
        this.rear = 0;
        this.modCount = 0;
    }

    /**
     * Removes and returns the first element from the ArrayList.
     *
     * @return the first element from the ArrayList
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        T result = list[front];
        list[front] = null;
        for (int i = 0; i < this.rear; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.rear--;
        this.modCount++;
        return result;
    }

    /**
     * Removes and returns the last element from the ArrayList.
     *
     * @return the last element from the ArrayList
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        this.rear--;
        T result = list[rear];
        list[rear] = null;
        this.modCount++;

        return result;
    }

    /**
     * Removes the specified element from the ArrayList.
     *
     * @param element the element to be removed
     * @return the removed element
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T remove(T element) throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        if (!contains(element)) throw new EmptyCollectionException("Elemento não existe");
        int position = 0;
        for (int i = 0; i < this.rear; i++) {
            if (element.equals(list[i])) {
                position = i;
            }
        }

        T result = list[position];
        list[position] = null;
        for (int j = position; j < this.rear; j++) {
            this.list[j] = this.list[j + 1];
        }
        this.rear--;
        this.modCount++;
        return result;
    }

    /**
     * Returns the first element in the ArrayList.
     *
     * @return the first element in the ArrayList
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        return list[front];
    }

    /**
     * Returns the last element in the ArrayList.
     *
     * @return the last element in the ArrayList
     * @throws EmptyCollectionException if the ArrayList is empty
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        return list[rear - 1];
    }

    /**
     * Gets the element at the specified index in the ArrayList.
     *
     * @param i the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int i) {
        if (i >= this.rear || i < 0) throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + i);
        return list[i];
    }

    /**
     * Checks if the ArrayList contains the specified element.
     *
     * @param target the element to check for
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean contains(T target) {
        boolean found = false;
        int i = 0;
        while (i < this.rear && !found) {
            if (target.equals(list[i])) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Checks if the ArrayList is empty.
     *
     * @return true if the ArrayList is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return rear == 0;
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return the size of the ArrayList
     */
    @Override
    public int size() {
        return this.rear;
    }

    /**
     * Returns an iterator over the elements in the ArrayList.
     *
     * @return an iterator over the elements in the ArrayList
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator<>();
    }

    /**
     * Returns a string representation of the ArrayList.
     *
     * @return a string representation of the ArrayList
     */
    @Override
    public String toString() {
        String s = "ArrayList:\n";
        for (int i = 0; i < this.rear; i++) {
            s += list[i] + "\n";
        }
        return s;
    }

    /**
     * Iterator implementation for the ArrayList.
     */
    public class BasicIterator<T> implements Iterator<T> {
        private final int size;
        private final T[] items;
        private int current;
        private int expectedModCount;

        /**
         * Constructs an iterator for the ArrayList.
         */
        public BasicIterator() {
            this.items = (T[]) ArrayList.this.list;
            this.size = ArrayList.this.rear;
            this.current = 0;
            this.expectedModCount = ArrayList.this.modCount;
        }

        /**
         * Checks if there is a next element in the iteration.
         *
         * @return true if there is a next element, false otherwise
         * @throws ConcurrentModificationException if the ArrayList is modified during iteration
         */
        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("Concorrência");
            return (this.items[this.current] != null);
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            T temp = items[this.current];
            if (hasNext()) {
                this.current++;
            }
            return temp;
        }
    }
}
