package game.research.entityResearch.researchVisitors;

import game.research.entityResearch.iStructureResearchVisitor;
import game.entities.managers.StructureManager;

// Visitor to have the structure manager of player perform the upgrade on all structures worker radius
public class StructureWorkerRadiusResearchVisitor implements iStructureResearchVisitor {

    private int increaseAmount; // Amount to increase worker radius

    // Constructor
    public StructureWorkerRadiusResearchVisitor(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    // Visit structure manager to update all structs worker radius
    public void visitStructureManager(StructureManager structureManager) {
        structureManager.increaseWorkerRadius(increaseAmount);
    }

}
