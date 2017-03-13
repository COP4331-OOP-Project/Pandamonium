package game.entities.structures;

import game.entities.EntityId;
import game.entities.managers.MovementManager;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public class ObservationTower extends Structure {
    public ObservationTower(StructureStats stats, Location location , EntityId entityId , MovementManager movementManager){ super(stats, location, entityId, movementManager); }

}
