package game.visitors;

import game.entities.EntityId;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;

public class TransferWorkerVisitor implements  iWorkerTransferVisitor{
    private EntityId entityId;
    private WorkerTypeEnum workerTypeEnum;
    private Location location;

    public TransferWorkerVisitor(EntityId entityId, WorkerTypeEnum workerTypeEnum, Location location){
        this.entityId=entityId;
        this.workerTypeEnum=workerTypeEnum;
        this.location=location;
    }

    public Worker visitTransferVisitor(WorkerManager wm)throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException {
        return wm.transferWorker(entityId,workerTypeEnum,location);
    }
}
