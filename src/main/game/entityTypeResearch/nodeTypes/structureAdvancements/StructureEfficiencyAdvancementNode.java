package game.entityTypeResearch.nodeTypes.structureAdvancements;

import entityResearch.iStructureResearchVisitor;
import entityResearch.researchVisitors.StructureEfficiencyResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.semantics.Percentage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureEfficiencyAdvancementNode extends EntityTypeAdvancementNode {

    private final static Logger log = LogManager.getLogger(StructureEfficiencyAdvancementNode.class);

    private boolean isResearchCompleted;
    private EntitySubtypeEnum structureType;
    private Percentage increasePercentage;
    private StructureManager structureManager;

    public StructureEfficiencyAdvancementNode(StructureManager structureManager, EntitySubtypeEnum structureType, Percentage increasePercentage, String name, String description) {
        super(name, description);
        this.structureManager = structureManager;
        this.structureType = structureType;
        this.increasePercentage = increasePercentage;
        this.isResearchCompleted = false;
    }

    public StructureEfficiencyAdvancementNode(StructureManager structureManager, EntitySubtypeEnum structureType, Percentage increasePercentage, String name, String description, StructureEfficiencyAdvancementNode parent) {
        super(name, description, parent);
        this.structureManager = structureManager;
        this.structureType = structureType;
        this.increasePercentage = increasePercentage;
        this.isResearchCompleted = false;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() throws EntityTypeDoesNotExistException{
        this.isResearchCompleted = true;
        iStructureResearchVisitor visitor = new StructureEfficiencyResearchVisitor(this.structureType, this.increasePercentage);
        try {
            visitor.visitStructureManager(this.structureManager);
        } catch (StructureTypeDoesNotExist e) {
            log.error(e.getLocalizedMessage());
            throw new EntityTypeDoesNotExistException();
        }
    }
}

