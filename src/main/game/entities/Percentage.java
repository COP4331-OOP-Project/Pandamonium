package game.entities;

// Class to contain the percentage value of Entity health
public class Percentage {

    private double healthPercent;         // Saved health percent value of entity

    // Get health percentage
    public double getHealthPercent() {
        return healthPercent;
    }

    // Update to new entity health value
    public void updateHealthPercentage(double health) {
        this.healthPercent = health / 100;
        if (this.healthPercent > 1) this.healthPercent = 1;
        else if (this.healthPercent < 0) this.healthPercent = 0;
    }

}
