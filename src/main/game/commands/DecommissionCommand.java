package game.commands;

import game.entities.Entity;

// Decommission (destroy) the actor
public class DecommissionCommand extends Command {

    private Entity actor;              // Actor to decomission

    public DecommissionCommand(Entity actor) {
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute decommission command in Gameboard function
    public void exec() {}

}
