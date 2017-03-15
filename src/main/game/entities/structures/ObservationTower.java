package game.entities.structures;

import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public class ObservationTower extends Structure {
    public ObservationTower(StructureStats stats, Location location , EntityId entityId ,
                            PlacementManager placementManager, DeathNotifier notifier) {
        super(stats, location, entityId, placementManager, notifier);
    }

}
