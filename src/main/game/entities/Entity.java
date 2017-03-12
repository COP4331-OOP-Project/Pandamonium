package game.entities;

import java.util.LinkedList;
import java.util.Queue;

import game.commands.Command;
import game.entities.managers.MovementManager;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public abstract class Entity {
    protected PowerState powerState;
    protected Queue<Command> commandQueue;
    protected int health;
    protected Percentage healthPercent;
    protected Location location;
    private EntityId entityId;
    protected MovementManager movementManager;

    //TODO Get gameboard into constructor to create movementManager
    public Entity(Location location, EntityId entityId){
        this.commandQueue = new LinkedList<>();
        this.location = location;
        this.entityId = entityId;
    }

    public void accept(iTileActionVisitor v){ v.visitEntity(this); }             // Accept visitors

    // Health
    public double getCurrentHealth(){ return health; }                              // Return entity health
    public Percentage getHealthPercentage(){return healthPercent; }                 // Return entity health pct.
    public void takeDamage(double damage){                                          // Take damage to health
        this.health -= damage;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }
    public void heal(double healing){                                               // Heal for a given amount
        this.health += healing;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }

    // Command queue management
    public void addCommandToQueue(Command command){ commandQueue.offer(command); }  // Add new command to queue
    public void doTurn(){
        if(commandQueue.peek().getDuration()==0){
            commandQueue.poll().exec();
        }
        else{
            commandQueue.peek().iterateDuration();
        }
        //Yes it violates TDA but for now is the only way to do it based on how the Command class is created
    }                            // Iterate turn
    public Command nextCommand(){ return commandQueue.poll(); }                     // Next queue for new command or decrement turn count
    public Command peekCommand(){ return commandQueue.peek(); }                     // Peek at next command
    public boolean isQueueEmpty(){ return commandQueue.isEmpty(); }                 // Test is queue is empty
    public void cancelQueuedCommands(){ commandQueue.clear(); }                     // Clear command queue

    // State
    public void powerDown(){ powerState = PowerState.POWERED_DOWN; }                // Set power down state
    public void powerUp(){ powerState = PowerState.POWERED_UP; }                    // Set power up state
    public void combatState(){ powerState = PowerState.COMBAT; }                    // Set combat state on entity
    public void standby(){ powerState = PowerState.STANDBY; }                       // Set standby state on entity
    public PowerState getPowerState(){ return powerState; }                         // Get power state
    public void setPowerState(PowerState state){ powerState = state; }              // Set power state

    // Decommission
    public void decommissionEntity(){ /* TODO: Implement decommissionEntity */ }    // Destroy entity

    // Location
    public Location getLocation(){ return location; }                               // Get location of entity
    public int getLocationX(){return location.getX();}
    public int getLocationY(){return location.getY();}
    public void setLocation(Location location){ this.location = location; }         // Set location

    // Required Accessors
    public int getOwnerID(){ return entityId.getPlayerId(); }                       // Get owning player id
    public EntityId getEntityId(){ return entityId; }                               // Get entity id
    public int getInstanceId(){ return entityId.getInstanceId(); }                  // Get entity's instance id
    public MovementManager getMovementManager(){return movementManager;}

}
