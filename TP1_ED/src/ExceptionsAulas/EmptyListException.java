package ExceptionsAulas;

public class EmptyListException extends Throwable {
    public EmptyListException(String listIsEmpty) {
        super(listIsEmpty);
    }
}
