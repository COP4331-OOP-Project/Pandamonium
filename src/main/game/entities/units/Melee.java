package game.entities.units;

import game.entities.DeathNotifier;
import java.util.ArrayList;

import game.commands.CommandEnum;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Melee extends Unit {

    // Constructor
    public Melee(UnitStats stats, Location location, EntityId entityId,
                 PlacementManager placementManager, DeathNotifier notifier) {
                super(stats, location, entityId, placementManager, notifier);
    }

    public void onTurnEnded() {

    }
}
