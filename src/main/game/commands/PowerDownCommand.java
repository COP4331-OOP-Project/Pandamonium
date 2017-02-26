package game.commands;

import game.entities.Entity;

// Initiate a change to powered down state with an actor
public class PowerDownCommand extends Command {

    private Entity actor;                // Actor to power down

    public PowerDownCommand(Entity actor) {
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute power down
    public void exec() {
        // NEEDS TO CANCEL ALL COMMANDS IN ACTOR QUEUE
    }

}
