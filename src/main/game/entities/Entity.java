package game.entities;

import game.commands.Command;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Entity {

    protected PowerState powerState;
    protected Queue<Command> commandQueue;
    protected int ownerID;
    protected int health;
    protected Percentage healthPercent;

    public Entity(int ownerID){
        this.commandQueue = new LinkedList<Command>();
        this.ownerID = ownerID;
    }

    public abstract double getCurrentHealth();                  // Return entity health
    public abstract Percentage getHealthPercentage();           // Return entity health pct.
    public abstract void takeDamage(double damage);             // Take damage to health
    public abstract void heal(double healing);                  // Heal for a given amount
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

    // Decommission
    public abstract void decommissionEntity();                  // Destroy entity

    // Location
    public abstract Location getLocation();                     // Get location of entity
    public abstract void setLocation(Location location);        // Set location

    // Required Accessors
    public abstract int getOwnerID();                           // Get owning player id

}
