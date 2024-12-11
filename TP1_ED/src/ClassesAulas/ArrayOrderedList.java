package ClassesAulas;

import InterfacesAulas.OrderedListADT;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void add(T element) throws IllegalArgumentException {

        if (super.size() == list.length) {
            expandCapacity();
        }

        int position = 0;
        if (!(element instanceof Comparable<?>)) {
            throw new IllegalArgumentException("Not comparable");
        }
        Comparable<T> comparable = (Comparable<T>) element;

        while (position < rear && comparable.compareTo(list[position]) > 0) {
            ++position;
        }

        for (int i = rear; i > position; --i) {
            list[i] = list[i - 1];
        }

        list[position] = element;
        rear++;
        modCount++;
    }
}
