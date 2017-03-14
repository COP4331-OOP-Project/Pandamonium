package game.entities.managers.exceptions;

public class ArmyDoesNotExistException extends Exception{
	public ArmyDoesNotExistException(String message) {
        super(message);
    }

    public ArmyDoesNotExistException() {
        super();
    }
}
