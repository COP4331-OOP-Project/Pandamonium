package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

public class NutrientGenerator extends Worker {

    public NutrientGenerator(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.NUTRIENT_GENERATOR);
    }

    public Resource doProduction() {
        return new Resource(this.getWorkerStats().getProductionRate(), ResourceTypeEnum.NUTRIENTS);
    }
}
