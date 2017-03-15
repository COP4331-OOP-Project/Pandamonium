package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.managers.ResourceManager;
import game.entities.stats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.HarvestResourceVisitor;

public class FoodGatherer extends Worker {
    private ResourceManager resourceManager;

    public FoodGatherer(EntityId id, WorkerStats workerStats, Location location, ResourceManager resourceManager) {
        super(id, workerStats, location, WorkerTypeEnum.FOOD_GATHERER);
        this.resourceManager=resourceManager;
    }

    public Resource doProduction() {
        HarvestResourceVisitor harvestResourceVisitor = new HarvestResourceVisitor(this);
        return resourceManager.accept(harvestResourceVisitor);
    }

    public void onTurnEnded() {

    }
}
