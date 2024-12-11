package ExceptionsAulas;

public class ElementNotFoundException extends Throwable {
    public ElementNotFoundException(String elementNotFound) {
        super(elementNotFound);
    }
}
