package Trees;

import Lists.UnorderedLists.ArrayUnorderedList;
import Queues.CircularArrayQueue;
import exceptions.*;
import Interfaces.BinaryTreeADT;

import java.util.Iterator;

/**
 * ArrayBinaryTree implements the BinaryTreeADT interface using an array-based representation.
 *
 * @param <T> the type of elements stored in the binary tree
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        this.count = 0;
        this.tree = (T[])(new Object[CAPACITY]);
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root of the new tree
     */
    public ArrayBinaryTree(T element) {
        this.count = 1;
        this.tree = (T[])(new Object[CAPACITY]);
        this.tree[0] = element;
    }

    /**
     * Returns the root element of the binary tree.
     *
     * @return the root element of the binary tree
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("A lista está vazia");
        return this.tree[0];
    }

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the binary tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Returns the number of elements in the binary tree.
     *
     * @return the number of elements in the binary tree
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Checks if the binary tree contains the specified element.
     *
     * @param targetElement the element to check for in the binary tree
     * @return true if the element is in the binary tree, false otherwise
     */
    @Override
    public boolean contains(T targetElement) {
        boolean found = false;
        int i = 0;
        while (i < this.count && !found) {
            if (targetElement.equals(tree[i])) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in the tree
     * @return the reference to the specified target element
     * @throws ElementNotFoundException if the element is not found
     */
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < this.count && !found; ct++) {
            if (targetElement.equals(tree[ct])) {
                found = true;
            }
            temp = tree[ct];
        }
        if (!found) throw new ElementNotFoundException("binary tree");

        return temp;
    }

    /**
     * Returns an iterator for the in-order traversal of the binary tree.
     *
     * @return an iterator for the in-order traversal
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Helper method for in-order traversal.
     *
     * @param node     the current node in the traversal
     * @param tempList the list to store the traversal order
     */
    protected void inorder(int node, ArrayUnorderedList<T> tempList) {
        if (node < count) {
            if (tree[node] != null) {
                inorder((node + 1) * 2 - 1, tempList);
                tempList.addToRear(tree[node]);
                inorder((node + 1) * (2 + 1) - 1, tempList);
            }
        }
    }

    /**
     * Returns an iterator for the pre-order traversal of the binary tree.
     *
     * @return an iterator for the pre-order traversal
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        preorder(0, templist);
        return templist.iterator();
    }

    /**
     * Helper method for pre-order traversal.
     *
     * @param node     the current node in the traversal
     * @param templist the list to store the traversal order
     */
    protected void preorder(int node, ArrayUnorderedList<T> templist) {
        if (node < count) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                preorder(node * 2 + 1, templist);
                preorder((node + 1) * 2, templist);
            }
        }
    }

    /**
     * Returns an iterator for the post-order traversal of the binary tree.
     *
     * @return an iterator for the post-order traversal
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        postorder(0, templist);
        return templist.iterator();
    }

    /**
     * Helper method for post-order traversal.
     *
     * @param node     the current node in the traversal
     * @param templist the list to store the traversal order
     */
    protected void postorder(int node, ArrayUnorderedList<T> templist) {
        if (node < count) {
            if (tree[node] != null) {
                postorder(node * 2 + 1, templist);
                postorder((node + 1) * 2, templist);
                templist.addToRear(tree[node]);
            }
        }
    }

    /**
     * Returns an iterator for the level-order traversal of the binary tree.
     *
     * @return an iterator for the level-order traversal
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        levelorder(0, tempList);

        return tempList.iterator();
    }

    /**
     * Helper method for level-order traversal.
     *
     * @param root    the root of the binary tree
     * @param results the list to store the traversal order
     */
    protected void levelorder(int root, ArrayUnorderedList<T> results) {
        CircularArrayQueue<Integer> nodes = new CircularArrayQueue<>();
        int temp;
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            try {
                temp = nodes.dequeue();
                if (tree[temp] != null) {
                    results.addToRear(tree[temp]);

                } else {
                    results.addToRear(null);
                }

                if (tree[temp * 2 + 1] != null) {
                    nodes.enqueue(temp * 2 + 1);
                }
                if (tree[(temp + 1) * 2] != null) {
                    nodes.enqueue(temp * 2 + 2);
                }

            } catch (EmptyCollectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Returns a string representation of the binary tree in level-order.
     *
     * @return a string representation of the binary tree in level-order
     */
    @Override
    public String toString() {
        String s = "Iterator LevelOrder:";
        Iterator<T> itr = iteratorLevelOrder();
        while (itr.hasNext()) {
            s += itr.next() + " ";
        }
        return s;
    }
}
