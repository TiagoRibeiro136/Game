package Interfaces;

/**
 * The {@code BinarySearchTreeADT} interface defines the common operations for a binary search tree.
 * It extends the {@link BinaryTreeADT} interface.
 *
 * @param <T> the type of elements stored in the binary search tree
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {

    /**
     * Adds the specified element to the proper location in this binary search tree.
     *
     * @param element the element to be added to this tree
     */
    public void addElement(T element);

    /**
     * Removes and returns the specified element from this binary search tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree, or {@code null} if not found
     */
    public T removeElement(T targetElement);

    /**
     * Removes all occurrences of the specified element from this binary search tree.
     *
     * @param targetElement the element that the tree will have all instances of it removed
     */
    public void removeAllOccurrences(T targetElement);

    /**
     * Removes and returns the smallest element from this binary search tree.
     *
     * @return the smallest element from this tree, or {@code null} if the tree is empty
     */
    public T removeMin();

    /**
     * Removes and returns the largest element from this binary search tree.
     *
     * @return the largest element from this tree, or {@code null} if the tree is empty
     */
    public T removeMax();

    /**
     * Returns a reference to the smallest element in this binary search tree.
     *
     * @return a reference to the smallest element in this tree, or {@code null} if the tree is empty
     */
    public T findMin();

    /**
     * Returns a reference to the largest element in this binary search tree.
     *
     * @return a reference to the largest element in this tree, or {@code null} if the tree is empty
     */
    public T findMax();
}
