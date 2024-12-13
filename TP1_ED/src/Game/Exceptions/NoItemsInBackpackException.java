package Game.Exceptions;

/**
 * Custom exception to handle the scenario where a player tries to access
 * an empty backpack with no items available.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class NoItemsInBackpackException extends RuntimeException {
    /**
     * Constructs a NoItemsInBackpackException with a specified error message.
     *
     * @param message A description of the error indicating that the backpack is empty.
     */
    public NoItemsInBackpackException(String message) {
        super(message);
    }
}
