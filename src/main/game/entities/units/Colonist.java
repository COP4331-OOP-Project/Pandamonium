package game.entities.units;

import game.entities.EntityId;
import game.entities.managers.MovementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Colonist extends Unit{
    public Colonist(UnitStats stats, Location location, EntityId entityId, MovementManager movementManager){ super(stats, location, entityId, movementManager); }
}
