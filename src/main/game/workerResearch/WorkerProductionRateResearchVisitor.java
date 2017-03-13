package game.workerResearch;


import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.semantics.Percentage;

public class WorkerProductionRateResearchVisitor implements iWorkerResearchVisitor {

    private Percentage productionRateIncrease;
    private Integer productionRateChange;

    public WorkerProductionRateResearchVisitor(Percentage productionRateIncrease) {
        this.productionRateIncrease = productionRateIncrease;
    }

    public WorkerProductionRateResearchVisitor(int productionRateChange) {
        this.productionRateChange = productionRateChange;
    }

    public void visitWorkerManager(WorkerManager workerManager, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist {
        if (this.productionRateIncrease != null)
            workerManager.increaseProductionRateByPercentage(this.productionRateIncrease, workerType);
        else
            workerManager.changeProductionRateByAmount(this.productionRateChange, workerType);
    }
}
