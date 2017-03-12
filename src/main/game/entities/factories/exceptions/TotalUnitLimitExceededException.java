package game.entities.factories.exceptions;

public class TotalUnitLimitExceededException extends Exception {

    public TotalUnitLimitExceededException(String message) {
        super(message);
    }

    public TotalUnitLimitExceededException() {}

}
