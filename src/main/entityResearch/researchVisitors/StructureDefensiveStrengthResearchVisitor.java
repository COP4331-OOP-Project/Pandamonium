package entityResearch.researchVisitors;

import entityResearch.iStructureResearchVisitor;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;

// Visitor to have the structure manager of player perform the upgrade on subtype defense strength
public class StructureDefensiveStrengthResearchVisitor implements iStructureResearchVisitor {

    private int increaseAmount;          // Defense strength increase amount
    private EntitySubtypeEnum subtype;   // Structure subtype

    // Constructor
    public StructureDefensiveStrengthResearchVisitor(EntitySubtypeEnum structureType, int increaseAmount) {
        this.increaseAmount = increaseAmount;
        this.subtype = structureType;
    }

    // Visit structure manager to update subtype's defense strength value
    public void visitStructureManager(StructureManager structureManager) throws StructureTypeDoesNotExist {
        structureManager.increaseDefensiveStrength(this.subtype, this.increaseAmount);
    }

}

