package game.entities.units;

import game.gameboard.Location;

/**
 * Created by Alex on 2/21/17.
 */
public class Unit {
    private UnitStats stats;
    private Location location;

    public Unit(UnitStats stats, Location location){
        this.stats = stats;
        this.location = location;
    }
}
