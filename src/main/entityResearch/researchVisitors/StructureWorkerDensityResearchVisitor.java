package entityResearch.researchVisitors;

import entityResearch.iStructureResearchVisitor;
import game.entities.managers.StructureManager;

// Visitor to have the structure manager of player perform the upgrade on all structures worker density
public class StructureWorkerDensityResearchVisitor implements iStructureResearchVisitor {

    private int increaseAmount; // Amount to increase worker density

    // Constructor
    public StructureWorkerDensityResearchVisitor(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    // Visit structure manager to update all structs worker radius
    public void visitStructureManager(StructureManager structureManager) {
        structureManager.increaseWorkerDensity(increaseAmount);
    }

}

