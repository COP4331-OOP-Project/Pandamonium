package game.entities.stats;

import game.resources.Resource;

public class WorkerStats {

    private double productionRate;
    private Resource upkeep;

    public WorkerStats(double productionRate, Resource upkeep) {
        this.productionRate = productionRate;
        this.upkeep = upkeep;
    }

    public double getProductionRate() {
        return this.productionRate;
    }

    public void setProductionRate(double productionRate) {
        this.productionRate = productionRate;
    }

    public Resource getUpkeep() {
        return this.upkeep;
    }

    public void setUpkeep(Resource upkeep) {
        this.upkeep = upkeep;
    }
}
