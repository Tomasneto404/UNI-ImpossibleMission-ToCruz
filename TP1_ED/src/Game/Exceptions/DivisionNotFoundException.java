package Game.Exceptions;

/**
 * Custom exception to handle scenarios where a requested division cannot be found.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class DivisionNotFoundException extends RuntimeException {
    /**
     * Constructs a DivisionNotFoundException with a specified error message.
     *
     * @param message A description of the error indicating that the division was not found.
     */
    public DivisionNotFoundException(String message) {
        super(message);
    }
}
