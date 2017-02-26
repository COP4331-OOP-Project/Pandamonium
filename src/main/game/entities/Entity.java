package game.entities;

import game.commands.Command;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Entity {
    private Queue<Command> commandQueue;
    private PowerState powerState;

    public Entity(){
        commandQueue = new LinkedList<>();
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
    public abstract void doTurn();                              // Iterate turn
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
    public abstract void combatState();                         // Set combat state on entity
    public abstract void standby();                             // Set standby state on entity
    public PowerState getPowerState(){                          // Get power state
        return powerState;
    }
    public void setPowerState(PowerState state){                 // Set power state
        powerState=state;
    }

    // Decommission
    public abstract void decommissionEntity();                  // Destroy entity

    // Location
    public abstract Location getLocation();                     // Get location of entity
    public abstract void setLocation(Location location);        // Set location

    // Required Accessors
    public abstract int getOwnerID();                           // Get owning player id
    public abstract EntityId getEntityId();

}
