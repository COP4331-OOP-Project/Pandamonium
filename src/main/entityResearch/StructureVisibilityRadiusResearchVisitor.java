package entityResearch;

import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;

public class StructureVisibilityRadiusResearchVisitor implements iStructureResearchVisitor {


    private int visibilityRadiusIncrease;
    private EntitySubtypeEnum structureType;

    public StructureVisibilityRadiusResearchVisitor(EntitySubtypeEnum structureType, int visibilityRadiusIncrease) {
        this.visibilityRadiusIncrease = visibilityRadiusIncrease;
        this.structureType = structureType;
    }

    public void visitStructureManager(StructureManager structureManager) throws StructureTypeDoesNotExist {
        structureManager.increaseVisibilityRadius(this.structureType, this.visibilityRadiusIncrease);
    }

}
