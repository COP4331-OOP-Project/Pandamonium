package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

// Visitor to have the unit manager of player perform the upgrade on subtype attack strength
public class UnitDefensiveStrengthResearchVisitor implements iUnitResearchVisitor {

    private int increaseAmount;         // Defense strength increase amount
    private EntitySubtypeEnum subtype;  // Unit subtype

    // Constructor
    public UnitDefensiveStrengthResearchVisitor(EntitySubtypeEnum subtype, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = subtype;
    }

    // Visit unit manager to update subtype's defense strength
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseDefensiveStrength(this.subtype, this.increaseAmount);
    }

}
