package ClassesAulas;

import ExceptionsAulas.ElementNotFoundException;
import ExceptionsAulas.EmptyCollectionException;
import InterfacesAulas.BinaryTreeADT;

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected BinaryTreeNode<T> root;
    protected int count;

    public LinkedBinaryTree() {
        root = null;
        count = 0;
    }

    public LinkedBinaryTree(T root) {
        this.root = new BinaryTreeNode<T>(root);
        count = 1;
    }

    @Override
    public T getRoot() {
        return root.element;
    }

    @Override
    public boolean isEmpty() {
        return size () == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param element the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if an element not found
     * exception occurs
     */
    @Override
    public T find(T element) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(element, this.root);

        if (current == null) {
            throw new ElementNotFoundException("Element not found");
        }

        return current.element;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param element the element being sought in this tree
     * @param next the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T element, BinaryTreeNode<T> next) {

        if (next == null) {
            return null;
        }

        if (next.element.equals(element))
            return next;

        BinaryTreeNode<T> temp = findAgain(element, next.left);

        if (temp == null)
            temp = findAgain(element, next.right);

        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with
     * the root.
     *
     * @return an in order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inOrder (root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node to be used as the root
     * for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inOrder (BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inOrder (node.left, tempList);
            tempList.addToRear(node.element);
            inOrder (node.right, tempList);
        }
    }

    //postOrder
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postOrder (root, tempList);
        return tempList.iterator();
    }

    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            postOrder (node.left, tempList);
            postOrder (node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    //preOrder
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        preOrder (root, tempList);
        return tempList.iterator();
    }

    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preOrder (node.left, tempList);
            preOrder (node.right, tempList);
        }
    }

    //levelOrder
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<>();

        if (root != null) {
            queue.enqueue(root);
            while (!queue.isEmpty()) {
                try {
                    BinaryTreeNode<T> node = queue.dequeue();

                    tempList.addToRear(node.element);

                    if (node.left != null) {
                        queue.enqueue(node.left);
                    }
                    if (node.right != null) {
                        queue.enqueue(node.right);
                    }
                } catch (EmptyCollectionException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return tempList.iterator();
    }

}
