package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;

public class FoodGatherer extends Worker {

    public FoodGatherer(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.FOOD_GATHERER);
    }
}
