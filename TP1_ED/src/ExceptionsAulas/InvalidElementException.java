package ExceptionsAulas;

public class InvalidElementException extends Throwable {
    public InvalidElementException(String elementIsNull) {
        super(elementIsNull);
    }
}
