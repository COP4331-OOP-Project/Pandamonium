package game.entityTypeResearch.nodeTypes.unitAdvancements;

import entityResearch.iUnitResearchVisitor;
import entityResearch.researchVisitors.UnitEfficiencyResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.semantics.Percentage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnitEfficiencyAdvancementNode extends EntityTypeAdvancementNode {

    private final static Logger log = LogManager.getLogger(UnitEfficiencyAdvancementNode.class);

    private boolean isResearchCompleted;
    private EntitySubtypeEnum unitType;
    private Percentage increasePercentage;
    private UnitManager unitManager;

    public UnitEfficiencyAdvancementNode(UnitManager unitManager, EntitySubtypeEnum unitType, Percentage increasePercentage, String name, String description) {
        super(name, description);
        this.unitManager = unitManager;
        this.unitType = unitType;
        this.increasePercentage = increasePercentage;
        this.isResearchCompleted = false;
    }

    public UnitEfficiencyAdvancementNode(UnitManager unitManager, EntitySubtypeEnum unitType, Percentage increasePercentage, String name, String description, UnitEfficiencyAdvancementNode parent) {
        super(name, description, parent);
        this.unitManager = unitManager;
        this.unitType = unitType;
        this.increasePercentage = increasePercentage;
        this.isResearchCompleted = false;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() throws EntityTypeDoesNotExistException{
        this.isResearchCompleted = true;
        iUnitResearchVisitor visitor = new UnitEfficiencyResearchVisitor(this.unitType, this.increasePercentage);
        try {
            visitor.visitUnitManager(this.unitManager);
        } catch (UnitTypeDoesNotExistException e) {
            log.error(e.getLocalizedMessage());
            throw new EntityTypeDoesNotExistException();
        }
    }
}

