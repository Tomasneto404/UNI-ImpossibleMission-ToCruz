package Game.Exceptions;

/**
 * Custom exception to handle cases where a required field is not found in a JSON file.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class JSONFieldNotFoundException extends RuntimeException {
    /**
     * Constructs a JSONFieldNotFoundException with a specified error message.
     *
     * @param message A description indicating which field was not found in the JSON data.
     */
    public JSONFieldNotFoundException(String message) {
        super(message);
    }
}
