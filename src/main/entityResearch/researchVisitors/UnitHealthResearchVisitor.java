package entityResearch.researchVisitors;

import entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

// Visitor to have the unit manager of player perform the upgrade on subtype health value
public class UnitHealthResearchVisitor implements iUnitResearchVisitor {

    private int increaseAmount;         // Health increase amount
    private EntitySubtypeEnum subtype;  // Unit subtype

    // Constructor
    public UnitHealthResearchVisitor(EntitySubtypeEnum subtype, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = subtype;
    }

    // Visit unit manager to update subtype's health value
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseHealth(this.subtype, this.increaseAmount);
    }

}

