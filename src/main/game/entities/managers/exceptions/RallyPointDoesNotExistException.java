package game.entities.managers.exceptions;

public class RallyPointDoesNotExistException extends Exception{
	public RallyPointDoesNotExistException(String message) {
        super(message);
    }

    public RallyPointDoesNotExistException() {
        super();
    }
}
