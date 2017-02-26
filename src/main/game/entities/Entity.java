package game.entities;

import game.commands.Command;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Entity {
    private Queue<Command> commandQueue;
    private PowerState powerState;
    private Location location;
    private EntityId entityId;

    public Entity(){

    }

    public Entity(Location location, EntityId entityId){
        commandQueue = new LinkedList<>();
        powerUp();
        this.location=location;
        this.entityId=entityId;
    }

    public abstract double getCurrentHealth();                  // Return entity health
    public abstract Percentage getHealthPercentage();           // Return entity health pct.
    public abstract void takeDamage(double damage);             // Take damage to health
    public abstract void heal(double healing);                  // Heal for a given amount
    public abstract void accept(iTileActionVisitor v);          // Accept visitors

    // Command queue management
    public void addCommandToQueue(Command command){             //Add Command to queue
        commandQueue.add(command);
    }
    public void doTurn(){
        //TODO Fill this function out
    }
    public Command nextCommand(){                               // Next queue for new command or decrement turn count
        return commandQueue.poll();
    }
    public Command peekCommand(){                               // Peek at next command
        return commandQueue.peek();
    }
    public boolean isQueueEmpty(){                              // Test is queue is empty
        return  commandQueue.isEmpty();
    }
    public void cancelQueuedCommands(){                         // Clear command queue
        commandQueue.clear();
    }

    // State
    public void powerDown(){                                     // Set power down state
        setPowerState(PowerState.POWERED_DOWN);
    }
    public void powerUp(){                                       // Set power up state
        setPowerState(PowerState.POWERED_UP);
    }
    public PowerState getPowerState(){                          // Get power state
        return powerState;
    }
    public void setPowerState(PowerState state){                 // Set power state
        powerState=state;
    }

    // Decommission
    public abstract void decommissionEntity();                  // Destroy entity

    // Location
    public Location getLocation(){                              // Get location of entity
        return location;
    }
    public void moveToLocation(Location location){              // Set location
        this.location=location;
    }

    // Required Accessors
    public int getOwnerID(){                                    // Get owning player id
        return entityId.getPlayerId();
    }
    public EntityId getEntityId(){                              // Get entity id
        return entityId;
    }
    public int getInstance(){
        return entityId.getInstanceId();                        // Get entity's instance id
    }

}
