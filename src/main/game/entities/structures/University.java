package game.entities.structures;

import game.entities.EntityId;
import game.entities.managers.MovementManager;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.ResearchGenerator;
import game.gameboard.Location;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class University extends Structure {
    private Queue<ResearchGenerator> unassigned;
    private Queue<ResearchGenerator> researcher;

    public University(StructureStats stats, Location location , EntityId entityId , MovementManager movementManager){
        super(stats, location, entityId, movementManager);
        unassigned=new LinkedList<>();
        researcher=new LinkedList<>();
    }

    public void assignToResearcher(Location location){
        unassigned.peek().setLocation(location);
        researcher.add(unassigned.poll());
    }

    public void unassignResearcher(Location location){
        Iterator<ResearchGenerator> iterator = researcher.iterator();
        ResearchGenerator holder;
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

    public void addWorker(ResearchGenerator worker){
        unassigned.add(worker);
    }

    public void removeWorker(){
        unassigned.remove();
    }

    public int getTotalWorkers(){
        return unassigned.size() + researcher.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return researcher.size();
    }

    public void research(){

    }

}
