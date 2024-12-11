package ClassesAulas;

import ExceptionsAulas.ElementNotFoundException;
import ExceptionsAulas.EmptyCollectionException;
import InterfacesAulas.BinaryTreeADT;

import java.util.Iterator;

/**
 *
 * @author Tomas Neto
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    private final int DEFAULT_CAPACITY = 10;
    protected int count;
    protected T[] tree;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root
     * of the new tree
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[DEFAULT_CAPACITY];
        tree[0] = element;
    }


    @Override
    public T getRoot() {
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
     * @param element the element being sought in the tree
     * @return true if the element is in the tree
     * @throws ElementNotFoundException if an element not found
     * exception occurs
     */
    @Override
    public T find(T element) throws ElementNotFoundException {
        T temp = null;

        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++) {

            if (element.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }

        }

        if (!found) {
            throw new ElementNotFoundException("Element not found.");
        }


        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by
     * calling an overloaded, recursive inorder method
     * that starts with the root.
     *
     * @return an iterator over the binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inOrder (0, templist);
        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inOrder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length){
            if (tree[node] != null){
                inOrder (node*2+1, templist);
                templist.addToRear(tree[node]);
                inOrder ((node+1)*2, templist);
            }
        }

    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder(0, templist);
        return templist.iterator();
    }

    private void preorder(int node, ArrayUnorderedList<T> tmpList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                tmpList.addToRear(tree[node]);
                preorder(node * 2 + 1, tmpList);
                preorder((node + 1) * 2, tmpList);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        postorder(0, templist);
        return templist.iterator();
    }

    private void postorder(int node, ArrayUnorderedList<T> tmpList) {
        if (node < tree.length) {
            if (tree[node] != null) {
                preorder(node * 2 + 1, tmpList);
                preorder((node + 1) * 2, tmpList);
                tmpList.addToRear(tree[node]);
            }
        }
    }

    public void setTree(T[] tree) {
        this.tree = tree;
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        if (tree[0] != null) {
            queue.enqueue(0);

            while (!queue.isEmpty()) {
                try {
                    int node = queue.dequeue();
                    tempList.addToRear(tree[node]);
                    if ((node + 1) * 2 < tree.length) {        //if this node has children enqueue them in left to right order
                        //left child index is found using tge node index times 2 plus 1
                        if (tree[node * 2 + 1] != null) {
                            queue.enqueue(node * 2 + 1);
                        }
                        //right child index is found using the node index plus 1, times 2
                        if (tree[(node + 1) * 2] != null) {
                            queue.enqueue((node + 1) * 2);
                        }
                    }

                } catch (EmptyCollectionException ex) {
                }
            }
        }
        return tempList.iterator();
    }
}
