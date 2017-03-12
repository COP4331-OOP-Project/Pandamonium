package entityResearch;

import game.semantics.Percentage;

public interface iEntityResearchObservable {

    void attach(iEntityResearchObserver observer);
    void increaseVisibilityRadius(int increaseAmount);
    void increaseAttackStrength(int increaseAmount);
    void increaseDefenseStrength(int increaseAmount);
    void increaseArmorStrength(int increaseAmount);
    void increaseMovementRate(int increaseAmount);
    void increaseHealth(int increaseAmount);
    void increaseEfficiency(Percentage increasePercentage);
}
