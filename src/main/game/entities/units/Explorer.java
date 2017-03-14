package game.entities.units;

<<<<<<< HEAD
import game.entities.DeathNotifier;
=======
import game.commands.CommandEnum;
>>>>>>> Command Stuff
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

<<<<<<< HEAD
public class Explorer extends Unit {

    public Explorer(UnitStats stats, Location location, EntityId entityId,
                    PlacementManager placementManager, DeathNotifier notifier) {
            super(stats, location, entityId, placementManager, notifier);
    }

    public void onTurnEnded() {}
=======
public class Explorer extends Unit{
	private boolean isProspecting = false;
	
    public Explorer(UnitStats stats, Location location, EntityId entityId, PlacementManager placementManager){ 
    	super(stats, location, entityId, placementManager); 
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

	public void onTurnEnded() {
	}
>>>>>>> Command Stuff
}
