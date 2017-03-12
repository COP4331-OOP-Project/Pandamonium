package entityResearch;

import game.semantics.Percentage;

public interface iEntityResearchObserver {

    void onVisibilityRadiusIncreased(int increaseAmount);
    void onAttackStrengthIncreased(int increaseAmount);
    void onDefenseStrengthIncreased(int increaseAmount);
    void onArmorStrengthIncreased(int increaseAmount);
    void onMovementRateIncreased(int increaseAmount);
    void onHealthIncreased(int increaseAmount);
    void onEfficienyIncreased(Percentage increasePercentage);
}
