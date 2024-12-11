package InterfacesAulas;

import ExceptionsAulas.EmptyCollectionException;

public interface QueueADT<T> {
    public void enqueue(T item);
    public T dequeue() throws EmptyCollectionException;
    public T first() throws EmptyCollectionException;
    public boolean isEmpty();
    public int size();
    public String toString();
    
}
