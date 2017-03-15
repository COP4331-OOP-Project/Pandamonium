package game.resources;

import game.semantics.Percentage;

public class Resource {

    private double amount;
    private ResourceTypeEnum resourceType;

    public Resource(double amount, ResourceTypeEnum resourceType) {
        this.amount = amount;
        this.resourceType = resourceType;
    }

    public int getAmount() {
        return (int)this.amount;
    }

    public void decreaseAmountByPercentage(Percentage p) {
        this.amount *= (1 - p.getPercentageValue());
    }
    public void decreaseAmountByValue(double v){ this.amount -= v; }

    public Resource possibleAmountRetrieve(double v){
        if(v<=amount){
            return new Resource(v, resourceType);
        }
        else{
            return new Resource(amount, resourceType);
        }
    }

    public void increaseAmountByPercentage(Percentage p) {
        this.amount *= (1 + p.getPercentageValue());
    }
    public void increaseAmountByValue(double v){ this.amount += v; }

    public void combine(Resource resource){
        if(this.resourceType==resource.resourceType) {
            this.amount = this.amount + resource.amount;
        }
    }

    public ResourceTypeEnum getResourceType() {
        return this.resourceType;
    }
}
