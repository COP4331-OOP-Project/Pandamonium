package game.entities.units;

import game.entities.EntityId;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Colonist extends Unit{
    public Colonist(UnitStats stats, Location location, EntityId entityId){ super(stats, location, entityId); }
}
