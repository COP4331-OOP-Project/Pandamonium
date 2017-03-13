package game.entities.stats;

import game.resources.Resource;
import game.semantics.Percentage;

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

    public void increaseProductionRate(Percentage increasePerentage) {
        this.productionRate *= (1 + increasePerentage.getPercentageValue());
    }

    public void addToProductionRate(int changeAmount) {
        this.productionRate += changeAmount;
    }

    public Resource getUpkeep() {
        return this.upkeep;
    }

    public void setUpkeep(Resource upkeep) {
        this.upkeep = upkeep;
    }

    public void decreaseUpkeep(Percentage decreasePercentage) {
        this.upkeep.decreaseAmountByPercentage(decreasePercentage);
    }
}
