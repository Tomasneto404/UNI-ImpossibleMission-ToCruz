package Game.Exceptions;

/**
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class EnemiesStillAliveException extends RuntimeException {
    /**
     * Constructor to create an instance of the EnemiesStillAliveException.
     *
     * @param message A description of the error scenario.
     */
    public EnemiesStillAliveException(String message) {
        super(message);
    }
}
