package game.research.entityResearch;

import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.semantics.Percentage;

public interface iStructureResearchObservable {

    void attach(iStructureResearchObserver observer);
    void increaseVisibilityRadius(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void increaseAttackStrength(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void increaseDefensiveStrength(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void increaseArmorStrength(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void increaseHealth(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void increaseEfficiency(EntitySubtypeEnum subtype, Percentage increasePercentage) throws StructureTypeDoesNotExist;
    void increaseWorkerRadius(int increaseAmount);
    void increaseWorkerDensity(int increaseAmount);

}
