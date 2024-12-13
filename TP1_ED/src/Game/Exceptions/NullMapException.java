package Game.Exceptions;

/**
 * Custom exception to handle scenarios where a null map is encountered.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class NullMapException extends RuntimeException {

    /**
     * Constructs a NullMapException with a specified error message.
     *
     * @param message A description of the error indicating that a null map was encountered.
     */
    public NullMapException(String message) {
        super(message);
    }
}
