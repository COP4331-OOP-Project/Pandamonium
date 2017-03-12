package game.entities.managers.exceptions;

public class StructureDoesNotExistException extends Exception {

    public StructureDoesNotExistException(String message) {
        super(message);
    }

    public StructureDoesNotExistException() {
        super();
    }
}
