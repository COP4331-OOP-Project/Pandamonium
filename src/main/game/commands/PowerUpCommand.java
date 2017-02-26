package game.commands;

import game.entities.Entity;

// Initiate a change to powered up state with an actor
public class PowerUpCommand extends Command {

    private Entity actor;                // Actor to power up

    // Constructor
    public PowerUpCommand(Entity actor) {
        this.actor = actor;             // Set actor
        super.duration = 0;             // No wait till activation
    }

    // Execute power up from Gameboard function
    public void exec() {}

}
