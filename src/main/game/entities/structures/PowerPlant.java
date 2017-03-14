package game.entities.structures;

import game.entities.DeathNotifier;
import game.commands.CommandEnum;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.PeatGatherer;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.TransferWorkerVisitor;

import java.util.ArrayList;
import java.util.Iterator;

public class PowerPlant extends StructureWithWorker {

    public PowerPlant(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
        addCommand(CommandEnum.WORKER_GENERATE);
    }

    public void onTurnEnded() {

    }
}
