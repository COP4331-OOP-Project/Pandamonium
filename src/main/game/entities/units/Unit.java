package game.entities.units;

import game.commands.Command;
import game.entities.*;
import game.entities.Entity;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

// TODO: Maybe fix imports on subclasses
// TODO: Fix damage taking to account for defense
// TODO: Implement Entity abstract functions

public class Unit extends Entity implements iAttacker, iDefender, iMoveable {
    protected UnitStats stats;
    protected Location location;
    protected int orientation;

    public Unit(UnitStats stats, Location location, int ownerID){
        super(ownerID);

        this.stats = stats;
        this.location = location;
        this.health = stats.getHealth();
        this.healthPercent = new Percentage();
        this.orientation = 1;
        standby();
    }

    /* Accessors */
    public UnitStats getStats() { return stats; }
    public int getOrientation() { return orientation; }

    /* Mutators */
    public void setStats(UnitStats stats) { this.stats = stats; }
    public void setHealth(int health) { this.health = health; }
    public void setHealthPercent(Percentage healthPercent) { this.healthPercent = healthPercent; }
    public void setOrientation(int orientation) { this.orientation = orientation; }

    /* iEntity */
    public double getCurrentHealth(){ return health; }
    public Percentage getHealthPercentage(){return healthPercent; }
    public void takeDamage(double damage){
        this.health -= damage;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }
    public void heal(double healing){
        this.health += healing;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }
    public void accept(iTileActionVisitor v){}

    // Command queue management
    public void addCommandToQueue(Command command){ commandQueue.offer(command); }
    public void doTurn(){} // TODO: Implement doTurn
    public Command nextCommand(){ return commandQueue.poll(); }
    public Command peekCommand(){ return commandQueue.peek(); }
    public boolean isQueueEmpty(){ return commandQueue.isEmpty(); }
    public void cancelQueuedCommands(){ commandQueue.clear(); }

    // State
    public void powerDown(){ powerState = PowerState.POWERED_DOWN; }
    public void powerUp(){ powerState = PowerState.POWERED_UP; }
    public void combatState(){ powerState = PowerState.COMBAT; }
    public void standby(){ powerState = PowerState.STANDBY; }
    public PowerState getPowerState(){ return powerState; }
    public void setPowerState(PowerState state){ powerState = state; }

    // Decommission
    public void decommissionEntity(){} // TODO: Implement decommissionEntity

    // Location
    public Location getLocation(){ return location; }
    public void setLocation(Location location){ this.location = location; }

    // Required Accessors
    public int getOwnerID(){ return ownerID; }

    /* iAttacker */
    public double getDamage(){ return (double)stats.getOffPow(); }
    public int getRange(){ return 1; }

    /* iMoveable */
    public int getMoveDistance(){ return stats.getSpeed(); }
}
