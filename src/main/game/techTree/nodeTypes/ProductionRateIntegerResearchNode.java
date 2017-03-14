package game.techTree.nodeTypes;

import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.research.workerResearch.WorkerProductionRateResearchVisitor;
import game.research.workerResearch.iWorkerResearchVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductionRateIntegerResearchNode extends TechTreeNode {

    private final static Logger log = LogManager.getLogger(ProductionRateIntegerResearchNode.class);

    private boolean isResearchCompleted;
    private int productionRateChange;
    private WorkerManager workerManager;
    private WorkerTypeEnum workerType;

    public ProductionRateIntegerResearchNode(WorkerManager workerManager, String name, String description, TechNodeImageEnum imageEnum, WorkerTypeEnum workerType, int productionRateChange) {
        super(name, description, imageEnum);
        this.isResearchCompleted = false;
        this.productionRateChange = productionRateChange;
        this.workerManager = workerManager;
        this.workerType = workerType;
    }

    public ProductionRateIntegerResearchNode(WorkerManager workerManager, String name, String description, TechNodeImageEnum imageEnum, WorkerTypeEnum workerType, int productionRateChange, TechTreeNode... parents) {
        super(name, description, imageEnum, parents);
        this.isResearchCompleted = false;
        this.productionRateChange = productionRateChange;
        this.workerManager = workerManager;
        this.workerType = workerType;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() {
        iWorkerResearchVisitor workerResearchVisitor = new WorkerProductionRateResearchVisitor(this.productionRateChange);
        try {
            workerResearchVisitor.visitWorkerManager(this.workerManager, this.workerType);
        } catch (WorkerTypeDoesNotExist e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
