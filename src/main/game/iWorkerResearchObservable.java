package game;

import game.resources.Resource;

public interface iWorkerResearchObservable {
    void attach(iWorkerResearchObserver observer);
    void changeProductionRate(double productionRate);
    void changeUpkeep(Resource upkeep);
}
