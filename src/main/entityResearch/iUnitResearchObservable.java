package entityResearch;


import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.semantics.Percentage;

public interface iUnitResearchObservable {

    void attach(iUnitResearchObserver observer);
    void increaseVisibilityRadius(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void increaseAttackStrength(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void increaseDefensiveStrength(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void increaseArmorStrength(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void increaseHealth(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void increaseEfficiency(EntitySubtypeEnum subtype, Percentage increasePercentage) throws UnitTypeDoesNotExistException;
    void increaseMovementRange(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;

}
