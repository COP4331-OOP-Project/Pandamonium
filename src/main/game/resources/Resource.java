package game.resources;

import game.semantics.Percentage;

public class Resource {

    private double amount;
    private ResourceTypeEnum resourceType;

    public Resource(double amount, ResourceTypeEnum resourceType) {
        this.amount = amount;
        this.resourceType = resourceType;
    }

    public double getAmount() {
        return this.amount;
    }

    public void decreaseAmountByPercentage(Percentage p) {
        this.amount *= (1 - p.getPercentageValue());
    }

    public void increaseAmountByPercentage(Percentage p) {
        this.amount *= (1 + p.getPercentageValue());
    }

    public ResourceTypeEnum getResourceType() {
        return this.resourceType;
    }
}
