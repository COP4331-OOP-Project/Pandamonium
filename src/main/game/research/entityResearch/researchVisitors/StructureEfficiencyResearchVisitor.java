package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iStructureResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;
import game.semantics.Percentage;

// Visitor to have the structure manager of player perform the upgrade on subtype defense strength
public class StructureEfficiencyResearchVisitor implements iStructureResearchVisitor {

    private Percentage increasePercent;  // Efficiency increase percentage
    private EntitySubtypeEnum subtype;   // Structure subtype

    // Constructor
    public StructureEfficiencyResearchVisitor(EntitySubtypeEnum structureType, Percentage increasePercent) {
        this.increasePercent = increasePercent;
        this.subtype = structureType;
    }

    // Visit structure manager to update subtype's efficiency value
    public void visitStructureManager(StructureManager structureManager) throws StructureTypeDoesNotExist {
        structureManager.increaseEfficiency(this.subtype, this.increasePercent);
    }

}


