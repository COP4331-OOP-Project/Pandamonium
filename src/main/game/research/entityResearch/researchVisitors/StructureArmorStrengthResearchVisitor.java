package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iStructureResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;

// Visitor to have the structure manager of player perform the upgrade on subtype armor strength
public class StructureArmorStrengthResearchVisitor implements iStructureResearchVisitor {

    private int increaseAmount;          // Armor strength increase amount
    private EntitySubtypeEnum subtype;   // Structure subtype

    // Constructor
    public StructureArmorStrengthResearchVisitor(EntitySubtypeEnum structureType, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = structureType;
    }

    // Visit structure manager to update subtype's armor strength value
    public void visitStructureManager(StructureManager structureManager) throws StructureTypeDoesNotExist {
        structureManager.increaseArmorStrength(this.subtype, this.increaseAmount);
    }

}


