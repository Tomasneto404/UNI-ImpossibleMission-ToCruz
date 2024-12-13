package Game.Exceptions;

/**
 * Custom exception to handle the scenario where a player tries to leave a building incorrectly.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class LeaveBuildingException extends RuntimeException {
    /**
     * Constructs a LeaveBuildingException with a specified error message.
     *
     * @param message A description of the error indicating why the player cannot leave the building.
     */
    public LeaveBuildingException(String message) {
        super(message);
    }
}
