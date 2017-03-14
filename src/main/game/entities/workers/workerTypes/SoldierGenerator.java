package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.stats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

public class SoldierGenerator extends Worker {

    public SoldierGenerator(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.SOLDIER_GENERATOR);
    }

    public Resource doProduction() {
        return new Resource(this.getProductionRate(), ResourceTypeEnum.SOLDIER_POINTS);
    }

    public void onTurnEnded() {

    }
}
