package game.workerResearch;

import game.semantics.Percentage;

public interface iWorkerResearchObservable {
    void attach(iWorkerResearchObserver observer);
    void increaseProductionRateByPercentage(Percentage productionRateIncrease);
    void decreaseUpkeepByPercentage(Percentage upkeepDecrease);
}
