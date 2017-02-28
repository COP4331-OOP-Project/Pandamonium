package game.entities.units;

import game.gameboard.Location;
import game.entities.EntityId;

public class Explorer extends Unit {

    public Explorer(UnitStats stats, Location location, EntityId entityId){
        super(stats, location, entityId);
    }
}
