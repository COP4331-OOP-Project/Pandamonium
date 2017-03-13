package entityResearch;

import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.semantics.Percentage;

public interface iUnitResearchObserver {

    void onVisibilityRadiusIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void onAttackStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void onDefenseStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void onArmorStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void onHealthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;
    void onEfficiencyIncreased(EntitySubtypeEnum subtype, Percentage increasePercentage) throws UnitTypeDoesNotExistException;
    void onMovementRangeIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws UnitTypeDoesNotExistException;

}
