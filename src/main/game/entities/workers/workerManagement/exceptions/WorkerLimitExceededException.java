package game.entities.workers.workerManagement.exceptions;

public class WorkerLimitExceededException extends Exception {

    public WorkerLimitExceededException(String message) {
        super(message);
    }
}
