package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

// Visitor to have the unit manager of player perform the upgrade on subtype movement range
public class UnitMovementRangeResearchVisitor implements iUnitResearchVisitor {

    private int increaseAmount;         // Movement range increase amount
    private EntitySubtypeEnum subtype;  // Unit subtype

    // Constructor
    public UnitMovementRangeResearchVisitor(EntitySubtypeEnum subtype, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = subtype;
    }

    // Visit unit manager to update subtype's movement range
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseMovementRange(this.subtype, this.increaseAmount);
    }

}



