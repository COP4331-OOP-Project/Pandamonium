package game.entities.units;

import game.entities.EntityId;
import game.gameboard.Location;

/**
 * Created by David on 2/22/2017.
 */
public class Ranged extends Unit {

    public Ranged(UnitStats stats, Location location, EntityId entityId){
        super(stats, location, entityId);
    }
}
