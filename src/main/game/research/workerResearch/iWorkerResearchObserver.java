package game.research.workerResearch;


import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.semantics.Percentage;

public interface iWorkerResearchObserver {
    void onProductionRateIncreased(Percentage productionRateIncrease, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist;
    void onChangeProductionRateByAmount(int changerAmount, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist;
}
