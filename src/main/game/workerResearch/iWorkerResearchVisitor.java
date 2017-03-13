package game.workerResearch;

import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;

public interface iWorkerResearchVisitor {

    void visitWorkerManager(WorkerManager workerManager, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist;
}
