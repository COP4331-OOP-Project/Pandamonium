package game.resources;

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

    public ResourceTypeEnum getResourceType() {
        return this.resourceType;
    }
}
