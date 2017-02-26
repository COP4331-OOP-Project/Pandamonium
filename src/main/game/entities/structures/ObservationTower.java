package game.entities.structures;

import game.entities.EntityId;
import game.entities.Percentage;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class ObservationTower extends Structure {
    public ObservationTower(Location loc , EntityId entityId ){
        super(loc, entityId);
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
