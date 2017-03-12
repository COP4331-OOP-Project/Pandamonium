package game.entities;

// Class to contain the percentage value of Entity health
public class HealthPercentage {

    private double healthPercent;         // Saved health percent value of entity

    public HealthPercentage(){ this.healthPercent = 1; }

    // Get health percentage
    public double getHealthPercent() {
        return healthPercent;
    }

    // Update to new entity health value
    public void updateHealthPercentage(double health) {
        this.healthPercent = health / 10;       // TODO: Change denominator to reflect base health
        if (this.healthPercent > 1) this.healthPercent = 1;
        else if (this.healthPercent < 0) this.healthPercent = 0;
    }

}
