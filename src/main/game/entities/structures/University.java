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
import game.entities.workers.workerTypes.*;
import game.gameboard.Location;
import game.visitors.TransferWorkerVisitor;

import java.util.ArrayList;
import java.util.Iterator;

public class University extends StructureWithWorker {

    public University(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
    }

    public void research(){

    }

    public void onTurnEnded() {

    }
}
