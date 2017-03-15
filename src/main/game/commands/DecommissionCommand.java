package game.commands;

import game.entities.Entity;

// Decommission (destroy) the actor
public class DecommissionCommand extends Command {

    private Entity actor;              // Actor to decomission

    public DecommissionCommand(Entity actor) {
        super(0);       // No wait till activation
    	this.actor = actor;     // Set actor
    }

    // Execute decommission command on actor
    public void exec() {
        this.actor.decommissionEntity();
    }

}
