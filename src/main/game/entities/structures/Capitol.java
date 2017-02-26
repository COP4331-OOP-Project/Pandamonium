package game.entities.structures;

import game.entities.EntityId;
import game.entities.Percentage;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

import java.util.ArrayList;

public class Capitol extends Structure {
    //private ArrayList<worker> workers;
    //private ArrayList<worker> farmer;
    //private ArrayList<worker> generator;
    //private ArrayList<worker> miner;
    //private ArrayList<worker> breeder;

    public Capitol(Location loc , EntityId entityId ){
        super(loc, entityId);
    }

    public void assignToFarmer(){

    }

    public void unassignFarmer(){

    }

    public void assignToGenerator(){

    }

    public void unassignGenerator(){

    }

    public void assignToMiner(){

    }

    public void unassignMiner(){

    }

    public void addWorker(){

    }

    public void removeWorker(){

    }

    public void assignToBreeder(){

    }

    public void unassignBreeder(){

    }

    /*public Resource harvest(){

    }*/

    public double getCurrentHealth(){
        return 0;
    }
    public Percentage getHealthPercentage(){
        return null;
    }
    public void takeDamage(double damage){

    }
    public void heal(double healing){

    }
    public void accept(iTileActionVisitor v){

    }
    public void decommissionEntity(){

    }
}
