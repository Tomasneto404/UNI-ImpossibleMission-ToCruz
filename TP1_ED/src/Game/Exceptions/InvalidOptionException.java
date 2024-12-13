package Game.Exceptions;

/**
 * Custom exception to handle the scenario where an invalid option is selected by the player or system.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */

public class InvalidOptionException extends RuntimeException {

    /**
     * Constructs an InvalidOptionException with a specified error message.
     *
     * @param message A description of the invalid option encountered.
     */
    public InvalidOptionException(String message) {
        super(message);
    }
}
