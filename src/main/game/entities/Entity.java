package game.entities;

import game.commands.Command;
import game.commands.CommandEnum;
import game.commands.SubCommandEnum;
import game.commands.iCommandable;
import game.entities.managers.PlacementManager;
import game.gameboard.Location;
import game.gameboard.iTileObserver;
import game.iTurnObserver;
import game.visitors.iTileActionVisitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public abstract class Entity implements iCommandable, iTurnObserver, iTileObserver {
    protected PowerState powerState;
    protected Queue<Command> commandQueue;
    protected int health;
    protected HealthPercentage healthPercent;
    protected EntityId entityId;
    protected Location location;

    private ArrayList<SubCommandEnum> subCommands = new ArrayList<>();
    private ArrayList<CommandEnum> commands = new ArrayList<>();

    protected PlacementManager placementManager;
    protected DeathNotifier notifer;
    
    // Constructor
    public Entity(EntityId entityId, PlacementManager placementManager){
        this.commandQueue = new LinkedList<>();
        this.entityId = entityId;
        this.placementManager = placementManager;
    }

    // Constructor w/ DN
    public Entity(EntityId entityId, PlacementManager placementManager, DeathNotifier notifier) {
        this.commandQueue = new LinkedList<>();
        this.entityId = entityId;
        this.placementManager = placementManager;
        this.notifer = notifier;
        commands.add(CommandEnum.POWER_DOWN);
        commands.add(CommandEnum.DECOMMISSION);
        commands.add(CommandEnum.CANCEL_QUEUE);
    }
    
    public void accept(iTileActionVisitor v){ v.visitEntity(this); }             // Accept visitors

    // Health
    public double getCurrentHealth(){ return health; }                              // Return entity health
    public HealthPercentage getHealthPercentage(){return healthPercent; }           // Return entity health pct.

    public void takeDamage(double damage) {                                         // Take damage to health
        this.health -= damage;
        this.healthPercent.updateHealthPercentage((double)this.health);

        if (this.health <= 0)
            this.notifer.publishEntityDeath(this.entityId.getTypeId(), (EntitySubtypeEnum) this.entityId.getSubTypeId(), this.entityId, this.location);
    }

    public void heal(double healing) {                                               // Heal for a given amount
        this.health += healing;
        this.healthPercent.updateHealthPercentage((double)this.health);
    }

    // Instantly kill this entity
    public void instantDeath() {
        this.health = 0;
        this.healthPercent.updateHealthPercentage((double) this.health);
        this.notifer.publishEntityDeath(this.entityId.getTypeId(), (EntitySubtypeEnum) this.entityId.getSubTypeId(), this.entityId, this.location);
    }

    // Command queue management
    public void addCommandToQueue(Command command){ commandQueue.offer(command); }  // Add new command to queue

    // Iterate turn
    public void doTurn() {
        if(!commandQueue.isEmpty()) {
            if(commandQueue.peek().iterateDuration()){
                commandQueue.poll();
            }
        }
    }

    // Do turn for entity
    public void onTurnEnded() {
        doTurn();
    }

    public Command nextCommand(){ return commandQueue.poll(); }                     // Next queue for new command or decrement turn count
    public Command peekCommand(){ return commandQueue.peek(); }                     // Peek at next command
    public boolean isQueueEmpty(){ return commandQueue.isEmpty(); }                 // Test is queue is empty
    public void cancelQueuedCommands(){ commandQueue.clear(); }                     // Clear command queue

    // State

    // Set power down state
    public void powerDown() {
    	addCommand(CommandEnum.POWER_UP);
    	removeCommand(CommandEnum.POWER_DOWN);
    	powerState = PowerState.POWERED_DOWN; 

    }

    // Set power up state
    public void powerUp(){ 
    	addCommand(CommandEnum.POWER_DOWN);
    	removeCommand(CommandEnum.POWER_UP);
    	powerState = PowerState.POWERED_UP; 
    }

    public void combatState(){ powerState = PowerState.COMBAT; }                    // Set combat state on entity
    public void standby(){ powerState = PowerState.STANDBY; }                       // Set standby state on entity
    public PowerState getPowerState(){ return powerState; }                         // Get power state
    public void setPowerState(PowerState state){ powerState = state; }              // Set power state

    // Decommission entity
    public void decommissionEntity() {
        this.notifer.publishEntityDeath(this.entityId.getTypeId(), (EntitySubtypeEnum) this.entityId.getSubTypeId(), this.entityId, this.location);
    }

    // Required Accessors
    public int getOwnerID(){ return entityId.getPlayerId(); }                       // Get owning player id
    public EntityId getEntityId(){ return entityId; }                               // Get entity id
    public int getInstanceId(){ return entityId.getInstanceId(); }                  // Get entity's instance id
    public PlacementManager getPlacementManager(){return placementManager;}

    public void addCommand(CommandEnum command) {
    	commands.add(command);
    }

    public void addSubCommand(SubCommandEnum command) { subCommands.add(command); }
    
    public void removeCommand(CommandEnum command) {
    	commands.remove(command);
    }
    
    public ArrayList<CommandEnum> getCommands() { return commands; }

    public ArrayList<SubCommandEnum> getSubCommands() { return subCommands; }

    public Location getLocation() { return location; }
}
