package game.entities.structures;

import game.entities.EntityId;
import game.entities.Percentage;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class University extends Structure {
    //private ArrayList<worker> workers;
    //private ArrayList<worker> researcher;

    public University(Location loc , EntityId entityId ){
        super(loc, entityId);
    }

    public void assignToResearcher(){

    }

    public void unassignResearcher(){
        
    }

    public void addWorker(){

    }

    public void removeWorker(){

    }

    public void research(){

    }

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
