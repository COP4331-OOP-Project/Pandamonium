package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

// Visitor to have the unit manager of player perform the upgrade on subtype attack strength
public class UnitAttackStrengthResearchVisitor implements iUnitResearchVisitor {

    private int increaseAmount;         // Attack damage increase amount
    private EntitySubtypeEnum subtype;  // Unit subtype designation

    // Consructor
    public UnitAttackStrengthResearchVisitor(EntitySubtypeEnum subtype, int increaseAmount) {
        this.subtype = subtype;
        this.increaseAmount = increaseAmount;
    }

    // Visit unit manager to update subtype's attack strength
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseAttackStrength(this.subtype, this.increaseAmount);
    }

}
