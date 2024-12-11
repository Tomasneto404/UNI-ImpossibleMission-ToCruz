package ClassesAulas;

import ExceptionsAulas.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private boolean okToRemove;
    private int expectedModCount;
    private int current;
    private ArrayList<T> list;

    public ArrayIterator(int modcount, ArrayList<T> list) {
        expectedModCount = modcount;
        current = 0;
        okToRemove = false;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return (current != list.size());
    }

    @Override
    public T next() {
        if (expectedModCount != list.modCount) {
            throw new java.util.ConcurrentModificationException();
        }

        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        okToRemove = true;

        return list.list[current++];
    }

    @Override
    public void remove() {

        //expectedModCount
        if (expectedModCount != list.modCount) {
            throw new java.util.ConcurrentModificationException();
        }

        if (!this.okToRemove) {
            throw new IllegalStateException();
        }

        this.okToRemove = false;

        try {
            list.remove(list.list[--current]);
        } catch (EmptyCollectionException ex) {

        }

    }
}
