package ClassesAulas;

public class LinearNode<T> {

    private T element;
    private LinearNode<T> next;

    public LinearNode() {
        this.element = null;
        this.next = null;
    }

    public LinearNode(T element) {
        this.element = element;
    }

    protected void setElement(T element) {
        this.element = element;
    }

    protected T getElement() {
        return element;
    }

    protected void setNext(LinearNode<T> next) {
        this.next = next;
    }

    protected LinearNode<T> getNext() {
        return this.next;
    }

    protected boolean hasNext() {
        return this.next != null;
    }


}