package game.entities.units;

import game.entities.DeathNotifier;
import game.commands.CommandEnum;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Explorer extends Unit {

	private boolean isProspecting = false;
    public Explorer(UnitStats stats, Location location, EntityId entityId,
                    PlacementManager placementManager, DeathNotifier notifier) {
            super(stats, location, entityId, placementManager, notifier);
        	addCommand(CommandEnum.START_PROSPECTING);
    }

    public void startProspecting() {
    	removeCommand(CommandEnum.START_PROSPECTING);
    	addCommand(CommandEnum.STOP_PROSPECTING);
    	isProspecting = true;
    }
    
    public void stopProspecting() {
    	removeCommand(CommandEnum.STOP_PROSPECTING);
    	addCommand(CommandEnum.START_PROSPECTING);
    	isProspecting = false;
    }
    
    public boolean getProspecting() {
    	return isProspecting;
    }
    
    @Override
    public int getMoveDistance() {
    	if (isProspecting) {
    		return 1;
    	} else {
    		return stats.getSpeed();
    	}
    }

}
