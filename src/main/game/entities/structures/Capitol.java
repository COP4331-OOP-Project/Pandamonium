package game.entities.structures;

<<<<<<< HEAD
import game.entities.DeathNotifier;
=======
import game.commands.CommandEnum;
>>>>>>> Command Stuff
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public class Capitol extends Structure {
    //private ArrayList<worker> workers;
    //private ArrayList<worker> farmer;
    //private ArrayList<worker> generator;
    //private ArrayList<worker> miner;
    //private ArrayList<worker> breeder;

<<<<<<< HEAD
    public Capitol(StructureStats stats, Location location ,
                   EntityId entityId, PlacementManager placementManager, DeathNotifier notifier) {

        super(stats, location, entityId, placementManager, notifier);

=======
    public Capitol(StructureStats stats, Location location , EntityId entityId, PlacementManager placementManager){
        super(stats, location, entityId, placementManager);
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
        addCommand(CommandEnum.HEAL);
        addCommand(CommandEnum.WORKER_FARM);
        addCommand(CommandEnum.WORKER_MINE);
        addCommand(CommandEnum.WORKER_GENERATE);
>>>>>>> Command Stuff
    }

    public void assignToFarmer(){

    }

    public void unassignFarmer(){

    }

    public void assignToGenerator(){

    }

    public void unassignGenerator(){

    }

    public void assignToMiner(){

    }

    public void unassignMiner(){

    }

    public void addWorker(){

    }

    public void removeWorker(){

    }

    public void assignToBreeder(){

    }

    public void unassignBreeder(){

    }

    /*public Resource harvest(){

    }*/

    public void onTurnEnded() {

    }
}
