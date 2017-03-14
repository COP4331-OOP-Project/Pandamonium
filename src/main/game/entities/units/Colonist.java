package game.entities.units;

<<<<<<< HEAD
import game.entities.DeathNotifier;
=======
import java.util.ArrayList;

import game.commands.CommandEnum;
>>>>>>> Command Stuff
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Colonist extends Unit{
<<<<<<< HEAD

    // Constructor
    public Colonist(UnitStats stats, Location location, EntityId entityId,
                    PlacementManager placementManager, DeathNotifier notifier) {
                        super(stats, location, entityId, placementManager, notifier);
=======
    public Colonist(UnitStats stats, Location location, EntityId entityId, PlacementManager placementManager) { 
    	super(stats, location, entityId, placementManager); 
    	addCommand(CommandEnum.FOUND_CAPITOL);
	}

    public void onTurnEnded() {
>>>>>>> Command Stuff
    }

    public void onTurnEnded() {}
}
