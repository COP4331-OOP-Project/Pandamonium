package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

// Visitor to have the unit manager of player perform the upgrade on subtype armor strength
public class UnitArmorStrengthResearchVisitor implements iUnitResearchVisitor {

    private int increaseAmount;         // Armor strength increase amount
    private EntitySubtypeEnum subtype;  // Unit subtype

    // Constructor
    public UnitArmorStrengthResearchVisitor(EntitySubtypeEnum subtype, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = subtype;
    }

    // Visit unit manager to update subtype's armor strength
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseArmorStrength(this.subtype, this.increaseAmount);
    }

}
