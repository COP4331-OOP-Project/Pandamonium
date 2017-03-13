package game.workerResearch;

import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.semantics.Percentage;

public interface iWorkerResearchObservable {
    void attach(iWorkerResearchObserver observer);
    void increaseProductionRateByPercentage(Percentage productionRateIncrease, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist;
    void changeProductionRateByAmount(int changeAmount, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist;
}
