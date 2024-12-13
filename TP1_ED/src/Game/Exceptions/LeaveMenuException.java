package Game.Exceptions;

/**
 * Custom exception to handle the scenario where a player chooses to leave the game menu.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class LeaveMenuException extends RuntimeException {
    /**
     * Constructs a LeaveMenuException with a specified error message.
     *
     * @param message A description of the error indicating why the player is leaving the menu.
     */
    public LeaveMenuException(String message) {
        super(message);
    }
}
