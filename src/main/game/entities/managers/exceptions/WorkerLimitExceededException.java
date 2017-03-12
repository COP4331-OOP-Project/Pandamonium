package game.entities.managers.exceptions;

public class WorkerLimitExceededException extends Exception {

    public WorkerLimitExceededException(String message) {
        super(message);
    }
}
