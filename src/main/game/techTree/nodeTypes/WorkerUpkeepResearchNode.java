package game.techTree.nodeTypes;

import game.workerResearch.WorkerUpkeepResearchVisitor;
import game.workerResearch.iWorkerResearchVisitor;
import game.entities.managers.WorkerManager;
import game.semantics.Percentage;

public class WorkerUpkeepResearchNode extends TechTreeNode {

    private WorkerManager workerManager;
    private Percentage decreasePercentage;
    private boolean isResearchCompleted;

    public WorkerUpkeepResearchNode(WorkerManager workerManager, Percentage decreasePercentage, String name, String description) {
        super(name, description);
        this.workerManager = workerManager;
        this.decreasePercentage = decreasePercentage;
        this.isResearchCompleted = false;
    }

    public WorkerUpkeepResearchNode(WorkerManager workerManager, String name, String description, Percentage decreasePercentage, TechTreeNode... parents) {
        super(name, description, parents);
        this.workerManager = workerManager;
        this.decreasePercentage = decreasePercentage;
        this.isResearchCompleted = false;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() {
        iWorkerResearchVisitor workerResearchVisitor = new WorkerUpkeepResearchVisitor(this.decreasePercentage);
        workerResearchVisitor.visitWorkerManager(this.workerManager);
        this.isResearchCompleted = true;
    }
}
