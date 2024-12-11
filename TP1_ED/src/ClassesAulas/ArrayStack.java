package ClassesAulas;

import InterfacesAulas.StackADT;

import java.util.EmptyStackException;

public class ArrayStack<T> implements StackADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private T[] stack;
    private int top;
    private int size;

    {
        top = 0;
        size = 0;
    }

    public ArrayStack() {
        stack = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int capacity) {
        stack = (T[]) new Object[capacity];
    }

    @Override
    public void push(T item) {

        if (size() == stack.length) {
            expandCapacity();
        }

        stack[top++] = item;
        size++;

    }

    @Override
    public T pop() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T topItem = stack[top - 1];
        stack[top--] = null;
        size--;

        return topItem;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void expandCapacity() {
        T[] newStack = (T[]) new Object[stack.length * 2];

        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }

        stack = newStack;

    }

    @Override
    public String toString(){

        String str = "";



        return str;
    }

}
