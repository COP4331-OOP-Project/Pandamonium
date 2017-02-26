package game;

import game.resources.Resource;

public interface iWorkerResearchObserver {
    void onProductionRateChanged(double productionRate);
    void onUpkeepChanged(Resource upkeep);
}
