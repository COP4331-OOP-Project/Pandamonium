package game.WorkerResearch;


import game.entities.managers.WorkerManager;
import game.semantics.Percentage;

public class WorkerProductionRateResearchVisitor implements iWorkerResearchVisitor {

    private Percentage productionRateIncrease;

    public WorkerProductionRateResearchVisitor(Percentage productionRateIncrease) {
        this.productionRateIncrease = productionRateIncrease;
    }

    public void visitWorkerManager(WorkerManager workerManager) {
        workerManager.increaseProductionRateByPercentage(this.productionRateIncrease);
    }
}
