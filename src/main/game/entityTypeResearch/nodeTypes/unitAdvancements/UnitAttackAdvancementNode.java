package game.entityTypeResearch.nodeTypes.unitAdvancements;

import entityResearch.iUnitResearchVisitor;
import entityResearch.researchVisitors.UnitAttackStrengthResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnitAttackAdvancementNode extends EntityTypeAdvancementNode {

    private final static Logger log = LogManager.getLogger(UnitAttackAdvancementNode.class);

    private boolean isResearchCompleted;
    private EntitySubtypeEnum unitType;
    private int increaseAmount;
    private UnitManager unitManager;

    public UnitAttackAdvancementNode(UnitManager unitManager, EntitySubtypeEnum unitType, int increaseAmount, String name, String description) {
        super(name, description);
        this.unitManager = unitManager;
        this.unitType = unitType;
        this.increaseAmount = increaseAmount;
        this.isResearchCompleted = false;
    }

    public UnitAttackAdvancementNode(UnitManager unitManager, EntitySubtypeEnum unitType, int increaseAmount, String name, String description, UnitAttackAdvancementNode parent) {
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
        iUnitResearchVisitor visitor = new UnitAttackStrengthResearchVisitor(this.unitType, this.increaseAmount);
        try {
            if(this.isResearchCompleted == false) {
                visitor.visitUnitManager(this.unitManager);
                this.isResearchCompleted = true;
            }
        } catch (UnitTypeDoesNotExistException e) {
            log.error(e.getLocalizedMessage());
            throw new EntityTypeDoesNotExistException();
        }
    }
}

