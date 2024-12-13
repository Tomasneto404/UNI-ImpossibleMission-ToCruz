package Game.Exceptions;

/**
 * Custom exception to handle the scenario where a player leaves the game.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class LeaveGameException extends RuntimeException {
    /**
     * Constructs a LeaveGameException with a specified error message.
     *
     * @param message A description of the error indicating why the game session is being terminated.
     */
    public LeaveGameException(String message) {
        super(message);
    }
}
