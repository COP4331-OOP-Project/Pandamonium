package game.entities.units;

import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Ranged extends Unit {

    // Constructor
    public Ranged(UnitStats stats, Location location, EntityId entityId,
                  PlacementManager placementManager, DeathNotifier notifier) {
            super(stats, location, entityId, placementManager, notifier);
    }

    public void onTurnEnded() {

    }
}
