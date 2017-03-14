package game.research.entityResearch;

import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.UnitManager;

public interface iUnitResearchVisitor {

    void visitUnitManager(UnitManager unitManager) throws UnitTypeDoesNotExistException;

}
