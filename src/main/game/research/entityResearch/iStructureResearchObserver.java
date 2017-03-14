package game.research.entityResearch;

import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.semantics.Percentage;

public interface iStructureResearchObserver {

    void onVisibilityRadiusIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void onAttackStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void onDefenseStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void onArmorStrengthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void onHealthIncreased(EntitySubtypeEnum subtype, int increaseAmount) throws StructureTypeDoesNotExist;
    void onEfficiencyIncreased(EntitySubtypeEnum subtype, Percentage increasePercentage) throws StructureTypeDoesNotExist;
    void onWorkerRadiusIncreased(int increaseAmount);
    void onWorkerDensityIncreased(int increaseAmount);

}
