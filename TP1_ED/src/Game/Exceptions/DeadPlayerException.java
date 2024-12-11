package Game.Exceptions;

public class DeadPlayerException extends RuntimeException {
    public DeadPlayerException(String message) {
        super(message);
    }
}
