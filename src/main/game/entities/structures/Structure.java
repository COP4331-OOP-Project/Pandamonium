package game.entities.structures;

import game.commands.Command;
import game.entities.EntityId;
import game.gameboard.Location;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Structure {
    private EntityId entityId;
    //private PowerState powerState;
    //private float defenseDamage;
    private Location location;
    private Queue<Command> CommandQueue;

    public Structure(){

    }

    public Structure(Location loc , EntityId entityId ){
        location=loc;
        this.entityId=entityId;
        CommandQueue = new LinkedList<>();
    }

    public void addCommandToQueue(Command command){
        CommandQueue.add(command);
    }

    public void cancelQueueCommand(){
        CommandQueue.clear();
    }
    //TODO add power state
    public void powerUp(){

    }

    public void standby(){

    }

    public void decomission(){

    }
}
