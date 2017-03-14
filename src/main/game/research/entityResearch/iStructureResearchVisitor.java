package game.research.entityResearch;

import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.managers.StructureManager;

public interface iStructureResearchVisitor {

    void visitStructureManager(StructureManager structureManager) throws StructureTypeDoesNotExist;
}
