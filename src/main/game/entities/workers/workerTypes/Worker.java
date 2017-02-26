package game.entities.workers.workerTypes;

import game.entities.workers.workerStats.WorkerStats;
import game.gameboard.Location;
import game.entities.EntityId;
import game.resources.Resource;

public abstract class Worker {
    private Location location;
    private EntityId id;
    private WorkerTypeEnum workerType;
    private WorkerStats workerStats;

    public Worker(EntityId id, WorkerStats workerStats, Location location, WorkerTypeEnum workerType) {
        this.id = id;
        this.workerStats = workerStats;
        this.location = location;
        this.workerType = workerType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EntityId getId() {
        return id;
    }

    public WorkerTypeEnum getWorkerType() {
        return workerType;
    }

    public double getProductionRate() {
        return this.workerStats.getProductionRate();
    }

    public void setProductionRate(double productionRate) {
        this.workerStats.setProductionRate(productionRate);
    }

    public Resource getUpkeep() {
        return this.workerStats.getUpkeep();
    }

    public void setUpkeep(Resource upkeep) {
        this.workerStats.setUpkeep(upkeep);
    }

    public WorkerStats getWorkerStats() { return this.workerStats; }

    public abstract Resource doProduction();
}
