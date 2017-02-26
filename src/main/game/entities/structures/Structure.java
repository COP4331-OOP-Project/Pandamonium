package game.entities.structures;

import java.util.LinkedList;
import java.util.Queue;

import game.commands.Command;
import game.entities.Entity;
import game.entities.EntityId;
import game.entities.Percentage;
import game.entities.PowerState;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public abstract class Structure extends Entity {

    public Structure(Location loc , EntityId entityId ){
        super(loc, entityId);
    }


    //TODO wait for command class to finish
    /**public void doTurn() {
        if (!CommandQueue.isEmpty()) {
            if (CommandQueue.peek().getDuration() == 0) {                               // Test if next cmd can fire
                CommandQueue.poll().exec();                                               // Execute next cmd
            } else CommandQueue.peek().iterateDuration();
        }

    }**/

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
    public void decommissionEntity(){

    }
}
