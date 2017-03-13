package entityResearch.researchVisitors;

import entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

// Visitor to have the unit manager of player perform the upgrade on subtype visibility
public class UnitVisibilityRadiusResearchVisitor implements iUnitResearchVisitor {

    private int visibilityRadiusIncrease;   // Visibility upgrade amount
    private EntitySubtypeEnum subtype;     // Unit subtype

    // Construct new visibility research visitor upgrade for
    // specified unit subtype for a visibility increase of X amount
    public UnitVisibilityRadiusResearchVisitor(EntitySubtypeEnum subtype, int increaseAmount) {
        this.visibilityRadiusIncrease = increaseAmount;
        this.subtype = subtype;
    }

    // Visit specified manager to perform upgrade to all observers
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseVisibilityRadius(this.subtype, this.visibilityRadiusIncrease);
    }

}
