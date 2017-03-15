package game.commands;

import game.entities.iMoveable;
import game.gameboard.Location;
import game.gameboard.Tile;

/**
 * Initiate a move with an actor
 */
public class MoveCommand extends Command {

    private iMoveable actor;     // Actor
    private Location target;        // Target Tile of move
    private int direction;      // Direction of move

    // Constructor
    public MoveCommand(iMoveable actor, Location target, int direction, int duration) {

    	super(duration);                // Set duration of cmd
    	this.actor = actor;             // Set actor to move
        this.target = target;           // Set tile to move actor to
        this.direction = direction;     // Set direction of move
    }

    // Get direction of move
    public int getDirection() {
        return this.direction;
    }

    // Execute move from Gameboard function
    public void exec() {
        actor.setLocation(this.target);
    }

}
