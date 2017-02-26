package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

public class WorkerGenerator extends Worker {

    public WorkerGenerator(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.WORKER_GENERATOR);
    }

    public Resource doProduction() {
        return new Resource(this.getProductionRate(), ResourceTypeEnum.WORKER_POINTS);
    }
}
