package game.entities.structures;

import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.FoodGatherer;
import game.gameboard.Location;

import java.util.*;

public class Farm extends Structure {
    private Queue<FoodGatherer> unassigned;
    private Queue<FoodGatherer> farmer;

    public Farm(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager){
        super(stats, location, entityId, placementManager);
        unassigned=new LinkedList<>();
        farmer=new LinkedList<>();
    }

    public void assignToFarmer(Location location){
        unassigned.peek().setLocation(location);
        farmer.add(unassigned.poll());
    }

    public void unassignFarmer(Location location){
        Iterator<FoodGatherer> iterator = farmer.iterator();
        FoodGatherer holder;
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

    public void addWorker(FoodGatherer worker){
        unassigned.add(worker);
    }

    public void removeWorker(){
        unassigned.remove();
    }

    public int getTotalWorkers(){
        return unassigned.size() + farmer.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return farmer.size();
    }

    /*public Resource harvest(){

    }*/

    public void onTurnEnded() {

    }
}
