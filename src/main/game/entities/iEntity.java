package game.entities;

import game.commands.Command;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public interface iEntity {

    double getCurrentHealth();                  // Return entity health
    Percentage getHealthPercentage();           // Return entity health pct.
    void takeDamage(double damage);             // Take damage to health
    void heal(double healing);                  // Heal for a given amount
    void accept(iTileActionVisitor v);          // Accept visitors

    // Command queue management
    void addCommandToQueue(Command command);    // Add new command to queue
    void doTurn();                              // Iterate turn
    Command nextCommand();                      // Next queue for new command or decrement turn count
    Command peekCommand();                      // Peek at next command
    boolean isQueueEmpty();                     // Test is queue is empty
    void cancelQueuedCommands();                // Clear command queue

    // State
    void powerDown();                           // Set power down state
    void powerUp();                             // Set power up state
    void combatState();                         // Set combat state on entity
    void standby();                             // Set standby state on entity
    PowerState getPowerState();                 // Get power state
    void setPowerState(PowerState state);       // Set power state

    // Decommission
    void decommissionEntity();                  // Destroy entity

    // Location
    Location getLocation();                     // Get location of entity
    void setLocation(Location location);        // Set location

    // Required Accessors
    int getOwnerID();                           // Get owning player id

}
