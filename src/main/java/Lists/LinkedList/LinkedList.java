package Lists.LinkedList;

/**
 * Represents a singly linked list.
 *
 * @param <T> the type of elements stored in the list
 */
public class LinkedList<T> {
    private int count;
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    /**
     * Adds an element to the beginning of the linked list.
     *
     * @param element the element to be added
     */
    public void add(T element) {
        LinkedListNode<T> node = new LinkedListNode<T>(element);
        node.setNext((head == null) ? tail : head);
        head = node;
        count++;
    }

    /**
     * Removes the specified element from the linked list.
     *
     * @param element the element to be removed
     */
    public void remove(T element) {
        if (head == null) {
            return;
        }

        boolean found = false;
        LinkedListNode<T> previous = null;
        LinkedListNode<T> current = head;

        while (!found) {
            if (element.equals(current.getElement())) {
                found = true;
                break;
            } else {
                previous = current;
                current = current.getNext();
            }
        }

        if (found) {
            if (previous == null) {
                head = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }

            if (current == tail) {
                tail = previous;
            }

            count--;
        }
    }

    /**
     * Gets the number of elements in the linked list.
     *
     * @return the number of elements in the linked list
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of elements in the linked list.
     *
     * @param count the number of elements to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets the head (first node) of the linked list.
     *
     * @return the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        return head;
    }

    /**
     * Sets the head (first node) of the linked list.
     *
     * @param head the new head of the linked list
     */
    public void setHead(LinkedListNode<T> head) {
        this.head = head;
    }

    /**
     * Gets the tail (last node) of the linked list.
     *
     * @return the tail of the linked list
     */
    public LinkedListNode<T> getTail() {
        return tail;
    }

    /**
     * Sets the tail (last node) of the linked list.
     *
     * @param tail the new tail of the linked list
     */
    public void setTail(LinkedListNode<T> tail) {
        this.tail = tail;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representation of the linked list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedListNode<T> current = head;

        sb.append("Lists.LinkedList.LinkedList: ");

        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }

        return sb.toString();
    }
}
