package ClassesAulas;

import InterfacesAulas.QueueADT;
import ExceptionsAulas.*;

public class CircularArrayQueue<T> implements QueueADT<T> {

    private final int DEFAULT_CAPACITY = 100;

    private int front, rear, count;

    private T[] queue;

    public CircularArrayQueue() {
        this.front = this.rear = this.count = 0;
        this.queue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public CircularArrayQueue(int size) {
        this.front = this.rear = this.count = 0;
        this.queue = (T[]) new Object[size];
    }

    private void expandCapacity() {
        T[] newQueue = (T[]) new Object[2 * queue.length];

        int i = front;
        for (int j = 0; j < count; j++) {
            newQueue[j] = queue[i++ % queue.length];
        }

        this.front = 0;
        this.rear = count - 1;

        queue = newQueue;
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length) {
            expandCapacity();
        }

        if (isEmpty()) {
            queue[0] = element;
            front = rear = 0;
        } else {
            rear = (rear + 1) % queue.length;
            queue[rear] = element;
        }
        count++;

    }

    @Override
    public T dequeue() throws EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException("Empty queue!");
        }
        T element = queue[front];
        queue[front] = null;

        if (size() > 1) {
            front = (front + 1) % queue.length;
        }
        count--;
        return element;
    }

    @Override
    public T first() throws EmptyCollectionException{
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty queue!");
        }

        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {

        String string = "Queue (primeiro para ultimo): \n";
        int i = front;
        for (int j = 0; j < count; j++) {
            string += queue[i++ % queue.length] + "\n";
        }

        return string;
    }
}
