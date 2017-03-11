package game.entities.structures;

import game.entities.EntityId;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.OreGatherer;
import game.gameboard.Location;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Mine extends Structure {
    private Queue<OreGatherer> unassigned;
    private Queue<OreGatherer> miner;

    public Mine(StructureStats stats, Location location , EntityId entityId ){
        super(stats, location, entityId);
        unassigned=new LinkedList<>();
        miner=new LinkedList<>();
    }

    public void assignToMiner(Location location){
        unassigned.peek().setLocation(location);
        miner.add(unassigned.poll());
    }

    public void unassignMiner(Location location){
        Iterator<OreGatherer> iterator = miner.iterator();
        OreGatherer holder;
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

    public void addWorker(OreGatherer worker){
        unassigned.add(worker);
    }

    public void removeWorker(){
        unassigned.remove();
    }

    public int getTotalWorkers(){
        return unassigned.size() + miner.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return miner.size();
    }

    /*public Resource harvest(){

    }*/

}
