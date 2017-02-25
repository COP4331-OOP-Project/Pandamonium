package game;

import game.resources.Resource;

public interface WorkerResearchObserver {
    void onProductionRateChanged(int productionRate);
    void onUpkeepChanged(Resource upkeep);
}
