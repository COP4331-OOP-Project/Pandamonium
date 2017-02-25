package game.entities.workers.workerManagement;

import game.WorkerResearchObserver;
import game.entities.EntityId;
import game.entities.EntityTypeEnum;
import game.entities.workers.workerTypes.FoodGatherer;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class WorkerFactory implements WorkerResearchObserver {

    private Map<WorkerTypeEnum, WorkerStats> workerProductionStatistics;
    private int playerId;

    public WorkerFactory(int playerId) {
        this.playerId = playerId;
        this.workerProductionStatistics = new HashMap<>();
        this.workerProductionStatistics.put(WorkerTypeEnum.FOOD_GATHERER, new WorkerStats(1, new Resource(1, ResourceTypeEnum.FOOD)));
        this.workerProductionStatistics.put(WorkerTypeEnum.ORE_GATHERER, new WorkerStats(1, new Resource(1, ResourceTypeEnum.ORE)));

    }

    public Worker createWorker(WorkerTypeEnum workerType, int id, Location location) {
        EntityId entityid = new EntityId(this.playerId, EntityTypeEnum.WORKER, workerType, id);
        switch(workerType) {
            case FOOD_GATHERER: {
                return new FoodGatherer(entityid, workerProductionStatistics.get(workerType), location);
            }
        }
        return null;
    }

    public void onProductionRateChanged(int productionRate) {
        for (Object o : this.workerProductionStatistics.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            WorkerStats workerStats = (WorkerStats) pair.getValue();
            workerStats.setProductionRate(productionRate);
        }
    }

    public void onUpkeepChanged(Resource upkeep) {
        for (Object o : this.workerProductionStatistics.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            WorkerStats workerStats = (WorkerStats) pair.getValue();
            workerStats.setUpkeep(upkeep);
        }
    }

}
