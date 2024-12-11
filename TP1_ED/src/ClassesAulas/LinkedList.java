package ClassesAulas;

import ExceptionsAulas.ElementNotFoundException;
import ExceptionsAulas.EmptyListException;
import ExceptionsAulas.InvalidElementException;

public class LinkedList<T> {

    private LinearNode<T> head, tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data){

        if (this.head == null) {
            this.head = new LinearNode<>(data);
            this.tail = this.head;
        }

        LinearNode<T> newNode = new LinearNode<>(data);

        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }


    private void dropFirst() {

        if (this.head != null) {
            this.head = this.head.getNext();
        }

    }

    private T remove(T element) throws InvalidElementException, EmptyListException, ElementNotFoundException {

        if (element == null) {
            throw new InvalidElementException("Element is null");
        }

        if (this.head == null) {
            throw new EmptyListException("List is empty");
        }

        LinearNode<T> current = this.head;
        LinearNode<T> previous = null;
        boolean found = false;

        while (current != null && !found) {
            if (element.equals(current.getElement())) {
                found = true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }

        if (!found) {
            throw new ElementNotFoundException("Element not found");
        }

        if (this.size == 1) {
            this.head = null;
        } else if (current.equals(this.head)) {
            this.head = this.head.getNext();
            current.setNext(null);
        } else {
            previous.setNext(current.getNext());
        }
        this.size--;
        return current.getElement();
    }



    public LinearNode<T> getHead() {
        return this.head;
    }

    public int getSize() {
        return this.size;
    }
}