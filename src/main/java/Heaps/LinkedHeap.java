package Heaps;

import Interfaces.HeapADT;
import Trees.LinkedBinaryTree;
import exceptions.EmptyCollectionException;

/**
 * Represents a binary heap implemented using linked nodes.
 *
 * @param <T> the type of elements stored in the heap
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    /**
     * Reference to the last node in the heap.
     */
    public HeapNode<T> lastNode;

    /**
     * Constructs an empty linked heap.
     */
    public LinkedHeap() {
        super();
    }

    /**
     * Adds the specified element to the heap in the appropriate position.
     *
     * @param obj the element to be added to the heap
     */
    @Override
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<>(obj);

        if (root == null) {
            root = node;
        } else {
            HeapNode<T> next_parent = getNextParentAdd();
            if (next_parent.getLeft() == null) {
                next_parent.setLeft(node);
            } else {
                next_parent.setRight(node);
            }
            node.parent = next_parent;
        }
        lastNode = node;
        count++;
        if (count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Reorders the heap to maintain the ordering property after adding a node.
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = this.lastNode;

        temp = next.getElement();

        while (next != root && ((Comparable<T>) temp).compareTo(next.getParent().getElement()) < 0) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    /**
     * Returns the next parent node for adding a new node.
     *
     * @return the next parent node for adding a new node
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.getParent().getLeft() != result)) {
            result = result.getParent();
        }
        if (result != root) {
            if (result.getParent().getRight() == null) {
                result = result.getParent();
            } else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null) {
                    result = (HeapNode<T>) result.getLeft();
                }
            }
        } else {
            while (result.getLeft() != null) {
                result = (HeapNode<T>) result.getLeft();
            }
        }
        return result;
    }

    /**
     * Removes and returns the element with the smallest value in the heap.
     *
     * @return the element with the smallest value in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Heap");
        }

        T minElement = root.getElement();

        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.getParent().getLeft() == lastNode) {
                lastNode.getParent().setLeft(null);
            } else {
                lastNode.getParent().setRight(null);
            }

            root.setElement(lastNode.getElement());
            lastNode = next_last;
            heapifyRemove();
        }
        count--;

        return minElement;
    }

    /**
     * Returns the node that will be the new last node after a remove operation.
     *
     * @return the node that will be the new last node after a remove operation
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.getParent().getLeft() == result)) {
            result = result.parent;
        }

        if (result != root) {
            result = (HeapNode<T>) result.getParent().getLeft();
        }

        while (result.getRight() != null) {
            result = (HeapNode<T>) result.getRight();
        }

        return result;
    }

    /**
     * Reorders the heap after removing the root element.
     */
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;

        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable<T>) left.getElement()).compareTo(right.getElement()) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = node.getElement();
        while ((next != null) && (((Comparable<T>) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable<T>) left.getElement()).compareTo(right.getElement()) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
        node.setElement(temp);
    }

    /**
     * Returns the element with the smallest value in the heap without removing it.
     *
     * @return the element with the smallest value in the heap
     * @throws EmptyCollectionException if the heap is empty
     */
    @Override
    public T findMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The heap is empty");
        }
        return root.getElement();
    }
}
