package game.entities.workers.workerManagement;

import game.entities.EntityId;
import game.entities.IdManager.IdManager;
import game.entities.IdManager.exceptions.IdDoesNotExistException;
import game.entities.IdManager.exceptions.IdLimitExceededException;
import game.entities.workers.workerManagement.exceptions.WorkerDoesNotExistException;
import game.entities.workers.workerManagement.exceptions.WorkerLimitExceededException;
import game.entities.workers.workerManagement.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;

import java.util.ArrayList;

public class WorkerIdManager {

    private static final int WORKER_ID_MIN = 0;
    private static final int WORKER_ID_MAX = 99;

    private WorkerFactory workerFactory;
    private ArrayList<Worker> workers;
    private IdManager idManager;

    public WorkerIdManager(int playerId) {
        this.workerFactory = new WorkerFactory(playerId);
        this.workers = new ArrayList<>();
        this.idManager = new IdManager(WORKER_ID_MIN, WORKER_ID_MAX);
    }

    public Worker createWorker(WorkerTypeEnum workerType, Location location) throws WorkerLimitExceededException, WorkerTypeDoesNotExist {
        Integer newId;
        try {
            newId = this.idManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new WorkerLimitExceededException("Worker limit has been reached. Cannot add new worker.");
        }

        Worker w = this.workerFactory.createWorker(workerType, newId, location);
        this.workers.add(w);
        return w;
    }

    public void removeWorker(EntityId id) throws WorkerDoesNotExistException {
        try {
            this.idManager.removeId(id.getInstanceId());
        } catch (IdDoesNotExistException e) {
            throw new WorkerDoesNotExistException("Cannot remove worker because it does not exist.");
        }

        for (Worker w : this.workers) {
            if (w.getId() == id) {
                this.workers.remove(w);
                break;
            }
        }
    }
}
