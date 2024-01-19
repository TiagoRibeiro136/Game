package Trees;

/**
 * BinaryTreeNode represents a node in a binary tree with a left and
 * right child.
 *
 * @param <T> the type of elements stored in the tree node
 */
public class BinaryTreeNode<T> {
    protected T element;
    protected BinaryTreeNode<T> left, right;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param obj the element that will become a part of
     *            the new tree node
     */
    protected BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }

    /**
     * Gets the element stored in this tree node.
     *
     * @return the element stored in this tree node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element of this tree node.
     *
     * @param element the element to be set in this tree node
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Gets the left child of this tree node.
     *
     * @return the left child of this tree node
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this tree node.
     *
     * @param left the left child to be set for this tree node
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Gets the right child of this tree node.
     *
     * @return the right child of this tree node
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this tree node.
     *
     * @param right the right child to be set for this tree node
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Returns the number of non-null children of this node.
     * This method may be able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;
        if (left != null)
            children = 1 + left.numChildren();
        if (right != null)
            children = children + 1 + right.numChildren();
        return children;
    }
}
