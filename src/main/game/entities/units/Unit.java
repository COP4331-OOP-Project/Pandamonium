package game.entities.units;

import game.gameboard.Location;

public abstract class Unit {
    private UnitStats stats;
    private Location location;

    public Unit(UnitStats stats, Location location){
        this.stats = stats;
        this.location = location;
    }
}
