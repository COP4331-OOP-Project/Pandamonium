package game.research.entityResearch.entityResearchTrees.nodeTypes.structureAdvancements;

import game.research.entityResearch.iStructureResearchVisitor;
import game.research.entityResearch.researchVisitors.StructureVisibilityRadiusResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureVisibilityAdvancementNode extends EntityTypeAdvancementNode {

    private final static Logger log = LogManager.getLogger(StructureVisibilityAdvancementNode.class);

    private boolean isResearchCompleted;
    private EntitySubtypeEnum structureType;
    private int increaseAmount;
    private StructureManager structureManager;

    public StructureVisibilityAdvancementNode(StructureManager structureManager, EntitySubtypeEnum structureType, int increaseAmount, String name, String description) {
        super(name, description);
        this.structureManager = structureManager;
        this.structureType = structureType;
        this.increaseAmount = increaseAmount;
        this.isResearchCompleted = false;
    }

    public StructureVisibilityAdvancementNode(StructureManager structureManager, EntitySubtypeEnum structureType, int increaseAmount, String name, String description, StructureVisibilityAdvancementNode parent) {
        super(name, description, parent);
        this.structureManager = structureManager;
        this.structureType = structureType;
        this.increaseAmount = increaseAmount;
        this.isResearchCompleted = false;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() throws EntityTypeDoesNotExistException{
        this.isResearchCompleted = true;
        iStructureResearchVisitor visitor = new StructureVisibilityRadiusResearchVisitor(this.structureType, this.increaseAmount);
        try {
            visitor.visitStructureManager(this.structureManager);
        } catch (StructureTypeDoesNotExist e) {
            log.error(e.getLocalizedMessage());
            throw new EntityTypeDoesNotExistException();
        }
    }
}

