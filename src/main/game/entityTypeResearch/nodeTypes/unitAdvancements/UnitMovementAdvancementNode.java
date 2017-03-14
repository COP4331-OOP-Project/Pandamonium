package game.entityTypeResearch.nodeTypes.unitAdvancements;

import entityResearch.iUnitResearchVisitor;
import entityResearch.researchVisitors.UnitMovementRangeResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnitMovementAdvancementNode extends EntityTypeAdvancementNode {

    private final static Logger log = LogManager.getLogger(UnitMovementAdvancementNode.class);

    private boolean isResearchCompleted;
    private EntitySubtypeEnum unitType;
    private int increaseAmount;
    private UnitManager unitManager;

    public UnitMovementAdvancementNode(UnitManager unitManager, EntitySubtypeEnum unitType, int increaseAmount, String name, String description) {
        super(name, description);
        this.unitManager = unitManager;
        this.unitType = unitType;
        this.increaseAmount = increaseAmount;
        this.isResearchCompleted = false;
    }

    public UnitMovementAdvancementNode(UnitManager unitManager, EntitySubtypeEnum unitType, int increaseAmount, String name, String description, UnitMovementAdvancementNode parent) {
        super(name, description, parent);
        this.unitManager = unitManager;
        this.unitType = unitType;
        this.increaseAmount = increaseAmount;
        this.isResearchCompleted = false;
    }

    public boolean isResearchCompleted() {
        return this.isResearchCompleted;
    }

    public void doResearch() throws EntityTypeDoesNotExistException{
        this.isResearchCompleted = true;
        iUnitResearchVisitor visitor = new UnitMovementRangeResearchVisitor(this.unitType, this.increaseAmount);
        try {
            visitor.visitUnitManager(this.unitManager);
        } catch (UnitTypeDoesNotExistException e) {
            log.error(e.getLocalizedMessage());
            throw new EntityTypeDoesNotExistException();
        }
    }
}

