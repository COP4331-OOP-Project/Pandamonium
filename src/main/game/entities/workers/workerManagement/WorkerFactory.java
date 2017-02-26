package game.entities.workers.workerManagement;

import game.WorkerResearchObserver;
import game.entities.EntityId;
import game.entities.EntityTypeEnum;
import game.entities.workers.workerTypes.*;
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
        this.workerProductionStatistics.put(WorkerTypeEnum.PEAT_GATHERER, new WorkerStats(1, new Resource(1, ResourceTypeEnum.PEAT)));
        this.workerProductionStatistics.put(WorkerTypeEnum.NUTRIENT_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.NUTRIENTS)));
        this.workerProductionStatistics.put(WorkerTypeEnum.METAL_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.METAL)));
        this.workerProductionStatistics.put(WorkerTypeEnum.POWER_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.POWER)));
        this.workerProductionStatistics.put(WorkerTypeEnum.WORKER_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.WORKER_POINTS)));
        this.workerProductionStatistics.put(WorkerTypeEnum.RESEARCH_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.RESEARCH_POINTS)));
        this.workerProductionStatistics.put(WorkerTypeEnum.SOLDIER_GENERATOR, new WorkerStats(1, new Resource(1, ResourceTypeEnum.SOLDIER_POINTS)));

    }

    public Worker createWorker(WorkerTypeEnum workerType, int id, Location location) {
        EntityId entityId = new EntityId(this.playerId, EntityTypeEnum.WORKER, workerType, id);
        switch(workerType) {
            case FOOD_GATHERER:
                return new FoodGatherer(entityId, workerProductionStatistics.get(workerType), location);
            case ORE_GATHERER:
                return new OreGatherer(entityId, workerProductionStatistics.get(workerType), location);
            case PEAT_GATHERER:
                return new PeatGatherer(entityId, workerProductionStatistics.get(workerType), location);
            case NUTRIENT_GENERATOR:
                return new NutrientGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case METAL_GENERATOR:
                return new MetalGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case POWER_GENERATOR:
                return new PowerGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case WORKER_GENERATOR:
                return new WorkerGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case RESEARCH_GENERATOR:
                return new ResearchGenerator(entityId, workerProductionStatistics.get(workerType), location);
            case SOLDIER_GENERATOR:
                return new SoldierGenerator(entityId, workerProductionStatistics.get(workerType), location);
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
