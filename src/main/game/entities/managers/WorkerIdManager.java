package game.entities.managers;

import game.entities.EntityId;
import game.entities.managers.IdManager.IdManager;
import game.entities.managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.factories.WorkerFactory;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;

public class WorkerIdManager {

    private static final int WORKER_ID_MIN = 0;
    private static final int WORKER_ID_MAX = 99;

    private WorkerFactory workerFactory;
    private IdManager idManager;

    public WorkerIdManager(WorkerFactory workerFactory) {
        this.workerFactory = workerFactory;
        this.idManager = new IdManager(WORKER_ID_MIN, WORKER_ID_MAX);
    }

    public Worker createWorker(WorkerTypeEnum workerType, Location location) throws WorkerLimitExceededException, WorkerTypeDoesNotExist {
        Integer newId;
        try {
            newId = this.idManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new WorkerLimitExceededException("Worker limit has been reached. Cannot add new worker.");
        }

        return this.workerFactory.createWorker(workerType, newId, location);
    }

    public void removeWorker(EntityId id) throws WorkerDoesNotExistException {
        try {
            this.idManager.removeId(id.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new WorkerDoesNotExistException("Cannot remove worker because it does not exist.");
        }
    }
}
