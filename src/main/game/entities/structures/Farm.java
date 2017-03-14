package game.entities.structures;

import game.commands.CommandEnum;
import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.managers.WorkerManager;
import game.entities.stats.StructureStats;
import game.gameboard.Location;


public class Farm extends StructureWithWorker {

    public Farm(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
        addCommand(CommandEnum.WORKER_FARM);
    }

}
