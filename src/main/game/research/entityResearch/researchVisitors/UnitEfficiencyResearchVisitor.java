package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iUnitResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;
import game.semantics.Percentage;

// Visitor to have the unit manager of player perform the upgrade on subtype efficiency
public class UnitEfficiencyResearchVisitor implements iUnitResearchVisitor {

    private Percentage increasePercent;  // Efficiency increase percentage
    private EntitySubtypeEnum subtype;   // Unit subtype

    // Constructor
    public UnitEfficiencyResearchVisitor(EntitySubtypeEnum subtype, Percentage increasePercent) {
        this.increasePercent = increasePercent;
        this.subtype = subtype;
    }

    // Visit unit manager to update subtype's efficiency
    public void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException {
        unitManager.increaseEfficiency(this.subtype, this.increasePercent);
    }

}


