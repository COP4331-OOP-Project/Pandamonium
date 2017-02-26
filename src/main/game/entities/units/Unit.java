package game.entities.units;

import game.entities.EntityId;
import game.gameboard.Location;
import game.entities.Entity;

public abstract class Unit extends Entity{
    private UnitStats stats;

    public Unit(UnitStats stats, Location location, EntityId entityId){
        super(location, entityId);
    }
}
