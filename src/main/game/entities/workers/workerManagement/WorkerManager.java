package game.entities.workers.workerManagement;

import game.entities.workers.workerManagement.exceptions.WorkerTypeDoesNotExist;
import game.iWorkerResearchObserver;
import game.entities.EntityId;
import game.entities.workers.workerManagement.WorkerIdManager;
import game.entities.workers.workerManagement.exceptions.WorkerDoesNotExistException;
import game.entities.workers.workerManagement.exceptions.WorkerLimitExceededException;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.resources.Resource;

import java.util.ArrayList;

public class WorkerManager implements iWorkerResearchObserver {

    private ArrayList<Worker> workers;
    private WorkerIdManager workerIdManager;

    public WorkerManager(int playerId) {
        this.workerIdManager = new WorkerIdManager(playerId);
    }


    public void addWorker(WorkerTypeEnum workerType, Location location) throws WorkerLimitExceededException, WorkerTypeDoesNotExist {
        this.workers.add(this.workerIdManager.createWorker(workerType, location));
    }

    public void removeWorker(EntityId id) throws WorkerDoesNotExistException {
        boolean removed = false;
        for (Worker w : this.workers) {
            if (w.getId() == id) {
                this.workers.remove(w);
                removed = true;
            }
        }

        if (!removed) throw new WorkerDoesNotExistException("Worker does not exist in WorkerManager");

        this.workerIdManager.removeWorker(id);
    }

    public Worker getWorker(EntityId id) {
        for (Worker w : this.workers) {
            if (w.getId() == id) {
                return w;
            }
        }

        return null;
    }

    public void onProductionRateChanged(double productionRate) {
        for (Worker w : this.workers) {
            w.setProductionRate(productionRate);
        }
    }

    public void onUpkeepChanged(Resource upkeep) {
        for (Worker w : this.workers) {
            w.setUpkeep(upkeep);
        }
    }



}
