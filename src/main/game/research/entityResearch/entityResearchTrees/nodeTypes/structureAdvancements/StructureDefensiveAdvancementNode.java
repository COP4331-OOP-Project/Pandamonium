package game.research.entityResearch.entityResearchTrees.nodeTypes.structureAdvancements;

import game.research.entityResearch.iStructureResearchVisitor;
import game.research.entityResearch.researchVisitors.StructureDefensiveStrengthResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;
import game.research.entityResearch.entityResearchTrees.nodeTypes.EntityTypeAdvancementNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureDefensiveAdvancementNode extends EntityTypeAdvancementNode {

    private final static Logger log = LogManager.getLogger(StructureDefensiveAdvancementNode.class);

    private boolean isResearchCompleted;
    private EntitySubtypeEnum structureType;
    private int increaseAmount;
    private StructureManager structureManager;

    public StructureDefensiveAdvancementNode(StructureManager structureManager, EntitySubtypeEnum structureType, int increaseAmount, String name, String description) {
        super(name, description);
        this.structureManager = structureManager;
        this.structureType = structureType;
        this.increaseAmount = increaseAmount;
        this.isResearchCompleted = false;
    }

    public StructureDefensiveAdvancementNode(StructureManager structureManager, EntitySubtypeEnum structureType, int increaseAmount, String name, String description, StructureDefensiveAdvancementNode parent) {
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
        iStructureResearchVisitor visitor = new StructureDefensiveStrengthResearchVisitor(this.structureType, this.increaseAmount);
        try {
            visitor.visitStructureManager(this.structureManager);
        } catch (StructureTypeDoesNotExist e) {
            log.error(e.getLocalizedMessage());
            throw new EntityTypeDoesNotExistException();
        }
    }
}

