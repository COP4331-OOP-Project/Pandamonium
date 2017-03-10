package game.entities.structures;

import game.entities.EntityId;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.PeatGatherer;
import game.gameboard.Location;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class PowerPlant extends Structure {
    private Queue<PeatGatherer> unassigned;
    private Queue<PeatGatherer> generator;

    public PowerPlant(StructureStats stats, Location location , EntityId entityId ){
        super(stats, location, entityId);
        unassigned=new LinkedList<>();
        generator=new LinkedList<>();
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

    /*public Resource harvest(){

    }*/

}
