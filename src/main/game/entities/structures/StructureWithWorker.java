package game.entities.structures;

import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.Worker;
import game.gameboard.Location;

import java.util.ArrayList;

public abstract class StructureWithWorker extends Structure {
    protected ArrayList<Worker> unassigned;
    public StructureWithWorker(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, DeathNotifier notifier){
        super(stats, location,entityId,placementManager, notifier);
        unassigned = new ArrayList<>();
    }

    public void addWorkers(ArrayList<Worker> workers){
        this.unassigned.addAll(workers);
    }

    public void removeWorker(){

    }
}
