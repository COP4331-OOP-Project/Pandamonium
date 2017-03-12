package game.entities.factories.exceptions;

public class UnitTypeLimitExceededException extends Exception {

    public UnitTypeLimitExceededException(String message) {
        super(message);
    }

    public UnitTypeLimitExceededException() {
        super();
    }

}
