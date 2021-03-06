package game.entities.factories;

import game.entities.EntityId;
import game.entities.EntityTypeEnum;
import game.entities.managers.ResourceManager;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.stats.WorkerStats;
import game.entities.workers.workerTypes.*;
import game.gameboard.Location;
import game.research.workerResearch.iWorkerResearchObserver;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.semantics.Percentage;

import java.util.HashMap;
import java.util.Map;

public class WorkerFactory implements iWorkerResearchObserver {

    private Map<WorkerTypeEnum, WorkerStats> workerProductionStatistics;
    private int playerId;
    private ResourceManager resourceManager;

    public WorkerFactory(int playerId, ResourceManager resourceManager) {
        this.resourceManager=resourceManager;
        this.playerId = playerId;
        this.workerProductionStatistics = new HashMap<>();
        this.workerProductionStatistics.put(WorkerTypeEnum.FOOD_GATHERER, new WorkerStats(1, new Resource(1, ResourceTypeEnum.FOOD)));
        this.workerProductionStatistics.put(WorkerTypeEnum.ORE_GATHERER, new WorkerStats(1, new Resource(1, ResourceTypeEnum.ORE)));
        this.workerProductionStatistics.put(WorkerTypeEnum.PEAT_GATHERER, new WorkerStats(1, new Resource(1, ResourceTypeEnum.PEAT)));
        this.workerProductionStatistics.put(WorkerTypeEnum.WORKER_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.WORKER_POINTS)));
        this.workerProductionStatistics.put(WorkerTypeEnum.RESEARCH_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.RESEARCH_POINTS)));
        this.workerProductionStatistics.put(WorkerTypeEnum.SOLDIER_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.SOLDIER_POINTS)));

    }

    public Worker createWorker(WorkerTypeEnum workerType, int id, Location location) throws WorkerTypeDoesNotExist {
        EntityId entityId = new EntityId(this.playerId, EntityTypeEnum.WORKER, workerType, id, id);
        switch(workerType) {
            case FOOD_GATHERER:
                return new FoodGatherer(entityId, workerProductionStatistics.get(workerType), location, resourceManager);
            case ORE_GATHERER:
                return new OreGatherer(entityId, workerProductionStatistics.get(workerType), location, resourceManager);
            case PEAT_GATHERER:
                return new PeatGatherer(entityId, workerProductionStatistics.get(workerType), location, resourceManager);
            case WORKER_GENERATOR:
                return new WorkerGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case RESEARCH_GENERATOR:
                return new ResearchGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case SOLDIER_GENERATOR:
                return new SoldierGenerator(entityId, workerProductionStatistics.get(workerType), location);
            default:
                throw new WorkerTypeDoesNotExist("Worker type " + workerType + "does not exist.");
        }
    }

    public void onProductionRateIncreased(Percentage productionRateIncrease, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist {
        WorkerStats workerStats = this.workerProductionStatistics.get(workerType);
        if (workerStats == null) throw new WorkerTypeDoesNotExist("Worker type " + workerType + " does not exist");

        workerStats.increaseProductionRate(productionRateIncrease);
    }

    public void onChangeProductionRateByAmount(int changeAmount, WorkerTypeEnum workerType) throws WorkerTypeDoesNotExist {
        WorkerStats workerStats = this.workerProductionStatistics.get(workerType);
        if (workerStats == null) throw new WorkerTypeDoesNotExist("Worker type " + workerType + " does not exist");

        workerStats.addToProductionRate(changeAmount);
    }

}
