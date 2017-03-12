package game.workerResearch;


import game.semantics.Percentage;

public interface iWorkerResearchObserver {
    void onProductionRateIncreased(Percentage productionRateIncrease);
    void onUpkeepDecreased(Percentage upkeepDecrease);
}
