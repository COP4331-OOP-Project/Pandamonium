package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iStructureResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;

// Visitor to have the structure manager of player perform the upgrade on subtype attack strength
public class StructureAttackStrengthResearchVisitor implements iStructureResearchVisitor {

    private int increaseAmount;          // Attack strength increase amount
    private EntitySubtypeEnum subtype;   // Structure subtype

    // Constructor
    public StructureAttackStrengthResearchVisitor(EntitySubtypeEnum structureType, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = structureType;
    }

    // Visit structure manager to update subtype's health value
    public void visitStructureManager(StructureManager structureManager) throws StructureTypeDoesNotExist {
        structureManager.increaseAttackStrength(this.subtype, this.increaseAmount);
    }

}

