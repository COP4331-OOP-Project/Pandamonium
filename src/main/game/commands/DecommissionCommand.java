package game.commands;

import game.entities.iEntity;

// Decommission (destroy) the actor
public class DecommissionCommand extends Command {

    private iEntity actor;              // Actor to decomission

    public DecommissionCommand(iEntity actor) {
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute decommission command in Gameboard function
    public void exec() {}

}
