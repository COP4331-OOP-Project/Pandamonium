package game.entities.structures;

import game.entities.Entity;
import game.entities.EntityId;
import game.entities.Percentage;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public class Structure extends Entity {
    protected StructureStats stats;


    public Structure(StructureStats stats, Location location , EntityId entityId ){
        super(location, entityId);

        this.stats = stats;
        this.health = stats.getHealth();
        this.healthPercent = new Percentage();
        standby();
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
