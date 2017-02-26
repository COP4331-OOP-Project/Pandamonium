package game.entities.structures;

import game.entities.EntityId;
import game.entities.Percentage;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Farm extends Structure {
    //private ArrayList<worker> workers;
    //private ArrayList<worker> farmer;

    public Farm(Location loc , EntityId entityId ){
        super(loc, entityId);
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
