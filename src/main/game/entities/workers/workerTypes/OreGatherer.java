package game.entities.workers.workerTypes;

import game.entities.EntityId;
import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;

public class OreGatherer extends Worker {

    public OreGatherer(EntityId id, WorkerStats workerStats, Location location) {
        super(id, workerStats, location, WorkerTypeEnum.ORE_GATHERER);
    }
}
