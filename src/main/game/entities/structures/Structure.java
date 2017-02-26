package game.entities.structures;

import game.commands.Command;
import game.entities.Entity;
import game.entities.EntityId;
import game.entities.PowerState;
import game.gameboard.Location;

import java.util.Queue;

public abstract class Structure extends Entity {
    //TODO ADD STATS

    public Structure(){

    }

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

    public void decomission(){

    }
}
