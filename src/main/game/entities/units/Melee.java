package game.entities.units;

import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Melee extends Unit{
    public Melee(UnitStats stats, Location location, EntityId entityId, PlacementManager placementManager){ super(stats, location, entityId, placementManager); }
}
