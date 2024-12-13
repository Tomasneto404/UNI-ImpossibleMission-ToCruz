package Game.Exceptions;

/**
 * Custom exception to handle scenarios where a null division is encountered.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class NullDivisionException extends RuntimeException {
    /**
     * Constructs a NullDivisionException with a specified error message.
     *
     * @param message A description of the error indicating that a null division was encountered.
     */
    public NullDivisionException(String message) {
        super(message);
    }
}
