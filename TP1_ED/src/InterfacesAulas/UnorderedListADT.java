package InterfacesAulas;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adds the specified element to this list at the front of the list
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to this list at the rear of the list
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T element);

    /**
     * Adds the specified element to this list after a certain element
     *
     * @param element the element to be added to this list
     */
    public void addAfter(T element, T target);
}
