package game.commands;

import game.entities.iEntity;

// Initiate a change to powered down state with an actor
public class PowerDownCommand extends Command {

    private iEntity actor;                // Actor to power down

    public PowerDownCommand(iEntity actor) {
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute power down
    public void exec() {
        // NEEDS TO CANCEL ALL COMMANDS IN ACTOR QUEUE
    }

}
