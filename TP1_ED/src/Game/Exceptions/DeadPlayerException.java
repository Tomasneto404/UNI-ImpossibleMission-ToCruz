package Game.Exceptions;

/**
 * Custom exception to handle scenarios where the player character has died.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class DeadPlayerException extends RuntimeException {
    /**
     * Constructs a DeadPlayerException with a specified error message.
     *
     * @param message A description of the error indicating that the player has died.
     */
    public DeadPlayerException(String message) {
        super(message);
    }
}
