package entityResearch;

import game.entities.managers.StructureManager;

public class StructureVisibilityRadiusResearchVisitor implements iStructureResearchVisitor {


    private int visibilityRadiusIncrease;

    public StructureVisibilityRadiusResearchVisitor(int visibilityRadiusIncrease) {
        this.visibilityRadiusIncrease = visibilityRadiusIncrease;
    }

    public void visitStructureManager(StructureManager structureManager) {
        structureManager.increaseVisibilityRadius(this.visibilityRadiusIncrease);
    }

}
