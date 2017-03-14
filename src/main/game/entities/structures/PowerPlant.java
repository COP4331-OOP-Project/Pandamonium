package game.entities.structures;

import game.entities.DeathNotifier;
import game.commands.CommandEnum;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.PeatGatherer;
import game.gameboard.Location;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class PowerPlant extends Structure {
    private Queue<PeatGatherer> unassigned;
    private Queue<PeatGatherer> generator;

    public PowerPlant(StructureStats stats, Location location , EntityId entityId,
                      PlacementManager placementManager, DeathNotifier notifier) {
        super(stats, location, entityId, placementManager, notifier);
        unassigned=new LinkedList<>();
        generator=new LinkedList<>();
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
        addCommand(CommandEnum.WORKER_GENERATE);
    }

    public void assignToGenerator(Location location){
        unassigned.peek().setLocation(location);
        generator.add(unassigned.poll());
    }

    public void unassignGenerator(Location location){
        Iterator<PeatGatherer> iterator = generator.iterator();
        PeatGatherer holder;
        while(iterator.hasNext()){
            holder=iterator.next();
            if(location.equals(holder.getLocation())) {
                holder.setLocation(location);
                unassigned.add(holder);
                iterator.remove();
                return;
            }
        }
    }

    public void addWorker(PeatGatherer worker){
        unassigned.add(worker);
    }

    public void removeWorker(){
        unassigned.remove();
    }

    public int getTotalWorkers(){
        return unassigned.size() + generator.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return generator.size();
    }
    /*public Resource harvest(){

    }*/


    public void onTurnEnded() {

    }
}
