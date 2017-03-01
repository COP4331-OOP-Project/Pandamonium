package game.entities.structures;

import game.entities.EntityId;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public class Farm extends Structure {
    //private ArrayList<worker> workers;
    //private ArrayList<worker> farmer;

    public Farm(StructureStats stats, Location location , EntityId entityId ){
        super(stats, location, entityId);
    }

    public void assignToFarmer(){

    }

    public void unassignFarmer(){

    }

    public void addWorker(){

    }

    public void removeWorker(){

    }

    /*public Resource harvest(){

    }*/
}
