package game.techTree.nodeTypes;

import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.semantics.Percentage;
import game.workerResearch.WorkerProductionRateResearchVisitor;
import game.workerResearch.iWorkerResearchVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductionRatePercentageResearchNode extends TechTreeNode {

    private final static Logger log = LogManager.getLogger(ProductionRatePercentageResearchNode.class);

    private boolean isResearchCompleted;
    private Percentage productionIncreasePercentage;
    private WorkerManager workerManager;
    private WorkerTypeEnum workerType;

    public ProductionRatePercentageResearchNode(WorkerManager workerManager, String name, String description, TechNodeImageEnum imageEnum, WorkerTypeEnum workerType, Percentage productionIncreasePercentage) {
        super(name, description, imageEnum);
        this.isResearchCompleted = false;
        this.productionIncreasePercentage = productionIncreasePercentage;
        this.workerManager = workerManager;
        this.workerType = workerType;
    }

    public ProductionRatePercentageResearchNode(WorkerManager workerManager, String name, String description, TechNodeImageEnum imageEnum, WorkerTypeEnum workerType, Percentage productionIncreasePercentage, TechTreeNode... parents) {
        super(name, description, imageEnum, parents);
        this.isResearchCompleted = false;
        this.productionIncreasePercentage = productionIncreasePercentage;
        this.workerManager = workerManager;
        this.workerType = workerType;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() {
        iWorkerResearchVisitor workerResearchVisitor = new WorkerProductionRateResearchVisitor(this.productionIncreasePercentage);
        try {
            workerResearchVisitor.visitWorkerManager(this.workerManager, this.workerType);
            this.isResearchCompleted = true;
        } catch (WorkerTypeDoesNotExist e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
