package game.entities.units;

import game.entities.*;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

// TODO: Maybe fix imports on subclasses
// TODO: Fix damage taking to account for defense

public abstract class Unit implements iEntity, iAttacker, iDefender, iMoveable {
    protected UnitStats stats;
    protected Location location;

    protected int health;
    protected Percentage healthPercent;
    protected int orientation;

    public Unit(UnitStats stats, Location location){
        this.stats = stats;
        this.location = location;
        this.health = stats.getHealth();
        this.healthPercent = new Percentage();
        this.orientation = 1;
    }

    /* Accessors */
    public UnitStats getStats() { return stats; }
    public Location getLocation() { return location; }
    public int getHealth() { return health; }
    public Percentage getHealthPercent() { return healthPercent; }
    public int getOrientation() { return orientation; }

    /* Mutators */
    public void setStats(UnitStats stats) { this.stats = stats; }
    public void setLocation(Location location) { this.location = location; }
    public void setHealth(int health) { this.health = health; }
    public void setHealthPercent(Percentage healthPercent) { this.healthPercent = healthPercent; }
    public void setOrientation(int orientation) { this.orientation = orientation; }

    /* iEntity */
    public double getCurrentHealth(){ return this.health; }
    public Percentage getHealthPercentage(){ return this.healthPercent; }
    public void takeDamage(double damage){
        this.health -= damage;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }

    public void heal(double healing){
        this.health += healing;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }

    public void accept(iTileActionVisitor v){}

    /* iAttacker */
    public double getDamage(){ return (double)stats.getOffPow(); }
    public int getRange(){ return 1; }

    /* iMoveable */
    public int getMoveDistance(){ return stats.getSpeed(); }
}
