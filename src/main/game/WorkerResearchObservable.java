package game;

import game.resources.Resource;

public interface WorkerResearchObservable {
    void attach(WorkerResearchObserver observer);
    void changeProductionRate(int productionRate);
    void changeUpkeep(Resource upkeep);
}
