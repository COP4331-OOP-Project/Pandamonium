package game.entities;

import game.commands.Command;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Entity {
<<<<<<< HEAD

    protected PowerState powerState;
    protected Queue<Command> commandQueue;
    protected int ownerID;
    protected int health;
    protected Percentage healthPercent;

    public Entity(int ownerID){
        this.commandQueue = new LinkedList<Command>();
        this.ownerID = ownerID;
=======
    private Queue<Command> commandQueue;
    private PowerState powerState;
    private Location location;
    private EntityId entityId;

    public Entity(Location location, EntityId entityId){
        commandQueue = new LinkedList<>();
        powerUp();
        this.location=location;
        this.entityId=entityId;
>>>>>>> master
    }

    public abstract double getCurrentHealth();                  // Return entity health
    public abstract Percentage getHealthPercentage();           // Return entity health pct.
    public abstract void takeDamage(double damage);             // Take damage to health
    public abstract void heal(double healing);                  // Heal for a given amount
<<<<<<< HEAD
    public abstract void accept(iTileActionVisitor v);          // Accept visitors

    // Command queue management
    public abstract void addCommandToQueue(Command command);    // Add new command to queue
    public abstract void doTurn();                              // Iterate turn
    public abstract  Command nextCommand();                      // Next queue for new command or decrement turn count
    public abstract Command peekCommand();                      // Peek at next command
    public abstract boolean isQueueEmpty();                     // Test is queue is empty
    public abstract void cancelQueuedCommands();                // Clear command queue

    // State
    public abstract void powerDown();                           // Set power down state
    public abstract void powerUp();                             // Set power up state
    public abstract void combatState();                         // Set combat state on entity
    public abstract void standby();                             // Set standby state on entity
    public abstract PowerState getPowerState();                 // Get power state
    public abstract  void setPowerState(PowerState state);       // Set power state
=======
    public void accept(iTileActionVisitor v){                   // Accept visitors
        v.visitEntity(this);
    }

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
>>>>>>> master

    // Decommission
    public abstract void decommissionEntity();                  // Destroy entity

    // Location
<<<<<<< HEAD
    public abstract Location getLocation();                     // Get location of entity
    public abstract void setLocation(Location location);        // Set location

    // Required Accessors
    public abstract int getOwnerID();                           // Get owning player id
=======
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
    public int getInstanceId(){
        return entityId.getInstanceId();                        // Get entity's instance id
    }
>>>>>>> master

}
