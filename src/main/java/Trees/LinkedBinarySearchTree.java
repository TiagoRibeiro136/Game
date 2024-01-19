package Trees;

import Interfaces.BinarySearchTreeADT;
import exceptions.*;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T>
        implements BinarySearchTreeADT<T> {
    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new
     *                binary search tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its key value. Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search
     *                tree
     */
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;
        if (isEmpty())
            root = temp;
        else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else
                        current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else
                        current = current.right;
                }
            }
        }
        count++;
    }

    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it. Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary
     *                      search tree
     * @throws ElementNotFoundException if an element not found
     *                                  exception occurs
     */
    public T removeElement(T targetElement)
            throws ElementNotFoundException {
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) targetElement).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;
                if (((Comparable) targetElement).compareTo(root.element) < 0)
                    current = root.left;
                else
                    current = root.right;
                while (current != null && !found) {
                    if (targetElement.equals(current.element)) {
                        found = true;
                        count--;
                        result = current.element;

                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;

                        if (((Comparable) targetElement).compareTo(current.element) < 0)
                            current = current.left;
                        else
                            current = current.right;
                    }
                } //while

                if (!found)
                    throw new ElementNotFoundException("binary search tree");
            }
        } // end outer if
        return result;
    }

    /**
     * Returns a reference to a node that will replace the one
     * specified for removal. In the case where the removed node has
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        if ((node.left == null) && (node.right == null))
            result = null;

        else if ((node.left != null) && (node.right == null))
            result = node.left;

        else if ((node.left == null) && (node.right != null))
            result = node.right;
        else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            if (node.right == current)
                current.left = node.left;

            else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }
        return result;
    }

    /**
     * Removes all occurrences of the specified target element from
     * the binary search tree.
     *
     * @param targetElement the element to be removed from the binary
     *                      search tree
     */
    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException {
        try {
            while (contains(targetElement)) {
                removeElement(targetElement);
            }
        } catch (Exception ElementNotFoundException) {
            ;
        }
    }

    /**
     * Removes and returns the smallest element from the binary search tree.
     *
     * @return the smallest element in the binary search tree
     */
    @Override
    public T removeMin() {
        T result = null;
        if (findMin() != null) {
            result = removeElement(findMin());
        }
        return result;
    }

    /**
     * Removes and returns the largest element from the binary search tree.
     *
     * @return the largest element in the binary search tree
     */
    @Override
    public T removeMax() {
        T result = null;
        if (findMax() != null) {
            result = removeElement(findMax());
        }
        return result;
    }

    /**
     * Returns the smallest element in the binary search tree.
     *
     * @return the smallest element in the binary search tree
     */
    @Override
    public T findMin() {
        if (isEmpty()) throw new ElementNotFoundException("árvore vazia");

        BinaryTreeNode<T> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getElement();
    }

    /**
     * Returns the largest element in the binary search tree.
     *
     * @return the largest element in the binary search tree
     */
    @Override
    public T findMax() {
        if (isEmpty()) throw new ElementNotFoundException("árvore vazia");

        BinaryTreeNode<T> current = root;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current.getElement();
    }
}
