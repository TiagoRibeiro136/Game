package Heaps;

import Trees.BinaryTreeNode;

/**
 * Represents a node in a binary heap data structure.
 *
 * @param <T> the type of data stored in the heap node
 */
public class HeapNode<T> extends BinaryTreeNode<T> {
    protected HeapNode<T> parent;

    /**
     * Creates a new heap node with the specified data.
     *
     * @param obj the data to be contained within the new heap node
     */
    public HeapNode(T obj) {
        super(obj);
        this.parent = null;
    }

    /**
     * Gets the parent node of this heap node.
     *
     * @return the parent node of this heap node
     */
    public HeapNode<T> getParent() {
        return parent;
    }

    /**
     * Sets the parent node of this heap node.
     *
     * @param parent the new parent node for this heap node
     */
    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
