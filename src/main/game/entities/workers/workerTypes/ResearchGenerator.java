package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.stats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

public class ResearchGenerator extends Worker {

    public ResearchGenerator(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.RESEARCH_GENERATOR);
    }

    public Resource doProduction() {
        return new Resource(this.getProductionRate(), ResourceTypeEnum.RESEARCH_POINTS);
    }
}
