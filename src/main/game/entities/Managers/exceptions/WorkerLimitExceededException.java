package game.entities.Managers.exceptions;

public class WorkerLimitExceededException extends Exception {

    public WorkerLimitExceededException(String message) {
        super(message);
    }
}
