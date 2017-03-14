package game.commands;

import game.entities.Entity;

// Initiate a change to powered up state with an actor
public class PowerUpCommand extends Command {

    private Entity actor;                // Actor to power up

    // Constructor
    public PowerUpCommand(Entity actor) {
    	super(0); //No wait for activation
        this.actor = actor;             // Set actor
    }

    // Execute power up from Gameboard function
    public void exec() {}

}
