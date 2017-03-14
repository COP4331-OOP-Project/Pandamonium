package game.visitors;

import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.Worker;

public interface iWorkerTransferVisitor {
    Worker visitTransferVisitor(WorkerManager wm)throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException;
}
