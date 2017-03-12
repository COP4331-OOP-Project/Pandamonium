package game.workerResearch;


import game.entities.managers.WorkerManager;
import game.semantics.Percentage;

public class WorkerUpkeepResearchVisitor implements iWorkerResearchVisitor {

    private Percentage upkeepDecrease;

    public WorkerUpkeepResearchVisitor(Percentage upkeepDecrease) {
        this.upkeepDecrease = upkeepDecrease;
    }

    public void visitWorkerManager(WorkerManager workerManager) {
        workerManager.decreaseUpkeepByPercentage(this.upkeepDecrease);
    }
}