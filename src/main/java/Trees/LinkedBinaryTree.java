package Trees;

import java.util.Iterator;

import Interfaces.BinaryTreeADT;
import exceptions.*;
import Lists.UnorderedLists.*;
import Queues.LinkedQueue.*;

/**
 * LinkedBinaryTree implements the BinaryTreeADT interface.
 *
 * @param <T> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the
     *                new binary tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    /**
     * Gets the root element of this binary tree.
     *
     * @return the root element of this binary tree
     * @throws EmptyCollectionException if the binary tree is empty
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The tree is empty");
        }
        return root.getElement();
    }

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the binary tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Gets the number of elements in this binary tree.
     *
     * @return the number of elements in this binary tree
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Checks if the binary tree contains the specified element.
     *
     * @param targetElement the element being sought in this tree
     * @return true if the tree contains the target element, false otherwise
     */
    @Override
    public boolean contains(T targetElement) {
        return (this.findAgain(targetElement, root) != null);
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws an ElementNotFoundException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if the target element is not found
     */
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null)
            throw new ElementNotFoundException("binary tree");

        return current.element;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next          the element to begin searching from
     * @return a reference to the specified target element, or null if not found
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;

        if (next.element.equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null)
            temp = findAgain(targetElement, next.right);

        return temp;
    }

    /**
     * Returns an iterator over the elements of this binary tree in inorder traversal.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inorder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    /**
     * Returns an iterator over the elements of this binary tree in preorder traversal.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        preorder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preorder(node.left, tempList);
            preorder(node.right, tempList);
        }
    }

    /**
     * Returns an iterator over the elements of this binary tree in postorder traversal.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postorder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postorder(node.left, tempList);
            postorder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    /**
     * Returns an iterator over the elements of this binary tree in level order traversal.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        levelorder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Performs a level order traversal on the binary tree.
     *
     * @param root    the root of the binary tree
     * @param results the list to store the traversal results
     */
    protected void levelorder(BinaryTreeNode<T> root, ArrayUnorderedList<T> results) {
        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        BinaryTreeNode<T> node = null;
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            try {
                node = (BinaryTreeNode<T>) nodes.dequeue();
                if (node.element != null) {
                    results.addToRear(node.element);
                } else {
                    results.addToRear(null);
                }

                if (node.getLeft() != null) {
                    nodes.enqueue(node.getLeft());
                }
                if (node.getRight() != null) {
                    nodes.enqueue(node.getRight());
                }
            } catch (EmptyCollectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Returns a string representation of the binary tree in level order.
     *
     * @return a string representation of the binary tree
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Iterator LevelOrder:");
        Iterator<T> itr = iteratorLevelOrder();
        while (itr.hasNext()) {
            s.append(itr.next()).append(" ");
        }
        return s.toString();
    }
}
