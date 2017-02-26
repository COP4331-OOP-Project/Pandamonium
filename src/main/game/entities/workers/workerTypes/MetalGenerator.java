package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

public class MetalGenerator extends Worker {

    public MetalGenerator(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.METAL_GENERATOR);
    }

    public Resource doProduction() {
        return new Resource(this.getWorkerStats().getProductionRate(), ResourceTypeEnum.METAL);
    }
}
