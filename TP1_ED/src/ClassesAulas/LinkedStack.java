package ClassesAulas;

import InterfacesAulas.StackADT;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackADT<T>{

    private LinearNode<T> head;
    private int size;

    public LinkedStack() {
        head = null;
        size = 0;
    }

    public LinkedStack (T element) {
        head = new LinearNode<T>(element);
        size = 1;
    }

    @Override
    public void push(T item) {
       if (head == null) {
           head = new LinearNode<>(item);
       }

       LinearNode<T> newNode = new LinearNode<>(item);
       newNode.setNext(head);
       head = newNode;
       size++;
    }

    @Override
    public T pop() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T elem = head.getElement();
        size--;

        if (head.getNext() == null) {
            head = null;
        } else {
            head = head.getNext();
        }

        return elem;
    }

    @Override
    public T peek() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return head.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }
}
