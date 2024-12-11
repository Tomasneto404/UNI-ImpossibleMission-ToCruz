package ClassesAulas;

/**
 * BinaryTreeNode represents a node in a binary tree with a left and
 * right child.
 */
public class BinaryTreeNode<T> {

    protected T element;
    protected BinaryTreeNode<T> right, left;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param element the element that will become a part of
     * the new tree node
     */
    public BinaryTreeNode(T element) {
        this.element = element;
        right = null;
        left = null;
    }

    /**
     * Returns the number of non-null children of this node.
     * This method may be able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;

        if (left != null) {
            children = 1 + left.numChildren();
        }

        if (right != null) {
            children = 1 + right.numChildren();
        }

        return children;
    }

}
