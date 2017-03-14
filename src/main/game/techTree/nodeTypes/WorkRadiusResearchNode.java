package game.techTree.nodeTypes;

import entityResearch.iStructureResearchVisitor;
import entityResearch.researchVisitors.StructureWorkerRadiusResearchVisitor;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkRadiusResearchNode extends TechTreeNode {

    private final static Logger log = LogManager.getLogger(WorkRadiusResearchNode.class);

    private boolean isResearchCompleted;
    private StructureManager structureManager;
    private int increaseAmount;

    public WorkRadiusResearchNode(StructureManager structureManager, String name, String description, TechNodeImageEnum imageEnum, int increaseAmount) {
        super(name, description, imageEnum);
        this.isResearchCompleted = false;
        this.structureManager = structureManager;
        this.increaseAmount = increaseAmount;
    }

    public WorkRadiusResearchNode(StructureManager structureManager, String name, String description, TechNodeImageEnum imageEnum, int increaseAmount, TechTreeNode... parents) {
        super(name, description, imageEnum, parents);
        this.isResearchCompleted = false;
        this.structureManager = structureManager;
        this.increaseAmount = increaseAmount;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() {
        iStructureResearchVisitor structureResearchVisitor = new StructureWorkerRadiusResearchVisitor(this.increaseAmount);
        try {
            structureResearchVisitor.visitStructureManager(this.structureManager);
        } catch (StructureTypeDoesNotExist e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
