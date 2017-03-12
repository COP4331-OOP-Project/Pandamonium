package game.entities.factories.exceptions;

public class StructureTypeLimitExceededException extends Exception {

    public StructureTypeLimitExceededException(String message) {
        super(message);
    }

    public StructureTypeLimitExceededException() {
        super();
    }
}
