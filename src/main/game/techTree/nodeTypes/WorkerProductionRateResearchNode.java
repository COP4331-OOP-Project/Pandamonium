package game.techTree.nodeTypes;

import game.workerResearch.WorkerProductionRateResearchVisitor;
import game.workerResearch.iWorkerResearchVisitor;
import game.entities.managers.WorkerManager;
import game.semantics.Percentage;

public class WorkerProductionRateResearchNode extends TechTreeNode {

    private WorkerManager workerManager;
    private Percentage increasePercentage;
    private boolean isResearchCompleted;

    public WorkerProductionRateResearchNode(WorkerManager workerManager, Percentage increasePercentage, String name, String description) {
        super(name, description);
        this.workerManager = workerManager;
        this.increasePercentage = increasePercentage;
        this.isResearchCompleted = false;
    }

    public WorkerProductionRateResearchNode(WorkerManager workerManager, String name, String description, Percentage increasePercentage, TechTreeNode... parents) {
        super(name, description, parents);
        this.workerManager = workerManager;
        this.increasePercentage = increasePercentage;
        this.isResearchCompleted = false;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() {
        iWorkerResearchVisitor workerResearchVisitor = new WorkerProductionRateResearchVisitor(this.increasePercentage);
        workerResearchVisitor.visitWorkerManager(this.workerManager);
        this.isResearchCompleted = true;
    }
}
