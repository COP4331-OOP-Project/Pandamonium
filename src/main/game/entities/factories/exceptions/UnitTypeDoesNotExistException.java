package game.entities.factories.exceptions;

public class UnitTypeDoesNotExistException extends Exception {

    public UnitTypeDoesNotExistException(String message) {
        super(message);
    }

    public UnitTypeDoesNotExistException() {}

}
