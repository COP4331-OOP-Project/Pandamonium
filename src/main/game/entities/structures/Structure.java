package game.entities.structures;

import game.commands.Command;
import game.entities.EntityId;
import game.entities.PowerState;
import game.gameboard.Location;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Structure {
    private EntityId entityId;
    private PowerState powerState;
    //private float defenseDamage;
    private Location location;
    private Queue<Command> CommandQueue;

    public Structure(){

    }

    public Structure(Location loc , EntityId entityId ){
        location=loc;
        this.entityId=entityId;
        CommandQueue = new LinkedList<>();
        powerState= PowerState.POWERED_UP;
    }

    public void addCommandToQueue(Command command){
        CommandQueue.add(command);
    }

    public void cancelQueueCommand(){
        CommandQueue.clear();
    }

    public void setPowerState(PowerState powerState){
        this.powerState=powerState;
    }

    public void powerUp(){
        setPowerState(PowerState.POWERED_UP);
    }

    public void powerDown(){
        setPowerState(PowerState.POWERED_DOWN);
    }

    public void standby(){
        setPowerState(PowerState.STANDBY);
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
