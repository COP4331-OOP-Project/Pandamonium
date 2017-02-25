package game.entities.units;

import game.entities.Percentage;
import game.gameboard.Location;

// TODO: Maybe fix imports on subclasses

public abstract class Unit {
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
}
