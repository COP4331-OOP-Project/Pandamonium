package game.commands;

import game.entities.Entity;

// Initiate a change to powered down state with an actor
public class PowerDownCommand extends Command {

    private Entity actor;                // Actor to power down

    public PowerDownCommand(Entity actor) {
    	super(0); //No wait for activation
        this.actor = actor;             // Set actor
    }

    // Execute power down
    public void exec() {
        this.actor.cancelQueuedCommands();
        this.actor.powerDown();
    }

}
