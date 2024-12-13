package Game.Exceptions;

/**
 * Custom exception to handle the scenario where a file cannot be found.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class FileNotFoundException extends RuntimeException {
    /**
     * Constructs a FileNotFoundException with a specified error message.
     *
     * @param message A description of the problem, indicating which file could not be found.
     */
    public FileNotFoundException(String message) {
        super(message);
    }
}
