package ClassesAulas;

import InterfacesAulas.BinarySearchTreeADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBinarySearchTree <T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    public LinkedBinarySearchTree() {
        super();
    }

    public LinkedBinarySearchTree(T root) {
        super(root);
    }

    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its key value. Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search
     * tree
     **/
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty()) {
            root = temp;
        } else {
            BinaryTreeNode<T> current = root;
            boolean added = false;

            while (!added) {

                if (comparableElement.compareTo(current.element) < 0) {

                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }

                } else {

                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else {
                        current = current.right;
                    }

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
     * @param element the element being sought in the binary
     * search tree
     * @throws NoSuchElementException if an element not found
     * exception occurs
     */
    @Override
    public T removeElement(T element) throws NoSuchElementException {

        T result = null;

        if (!isEmpty()) {

            if (((Comparable)element).equals(root.element)) {

                result = root.element;
                root = replacement(root);
                count--;

            } else {

                BinaryTreeNode<T> current, parent = root;
                boolean found = false;

                if (((Comparable)element).compareTo(root.element) < 0) {
                    current = root.left;
                } else {
                    current = root.right;
                }

                while (current != null && !found) {

                    if (element.equals(current.element)) {
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

                        if (((Comparable)element).compareTo(current.element) < 0) {
                            current = current.left;
                        } else {
                            current = current.right;
                        }

                    }

                }

                if (!found) {
                    throw new NoSuchElementException("Element not found");
                }

            }
        }

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

        if ((node.left == null) && (node.right == null)) {
            result = null;
        } else if (node.left != null && node.right == null) {
            result = node.left;
        } else if (node.left == null && node.right != null) {
            result = node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;

            while (current.left != null) {
                parent = current;
                current = current.left;
            }

            if (node.right == current) {
                current.left = node.left;
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }

        return result;
    }


    @Override
    public void removeAllOccurrences(T targetElement) {

        if (!isEmpty()) {
            boolean flag = false;

            while (!flag) {
                try {
                    removeElement(targetElement);
                } catch (NoSuchElementException e) {
                    flag = true;
                }
            }
        }
    }

    @Override
    public T removeMin() {
        T toRemove = findMin();

        removeAllOccurrences(toRemove);

        return toRemove;
    }

    @Override
    public T removeMax() {

        T toRemove = findMax();

        removeAllOccurrences(toRemove);

        return toRemove;
    }

    @Override
    public T findMin() {

        T result = null;

        if (!isEmpty()) {

            BinaryTreeNode<T> current = root;

            boolean found = false;

            while (!found) {

                if (current.left != null) {
                    current = current.left;
                } else {
                    found = true;
                    result = current.element;
                }
            }

        }

        return result;
    }

    @Override
    public T findMax() {
        T result = null;

        if (!isEmpty()) {

            BinaryTreeNode<T> current = root;

            boolean found = false;

            while (!found) {

                if (current.right != null) {
                    current = current.right;
                } else {
                    found = true;
                    result = current.element;
                }
            }

        }

        return result;
    }

    @Override
    public String toString() {
        Iterator<T> it = iteratorLevelOrder();
        String result = "";
        while (it.hasNext()) {
            result += it.next() + "\n";
        }
        return result;
    }

}
