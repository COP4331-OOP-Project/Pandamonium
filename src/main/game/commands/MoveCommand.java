package game.commands;

import game.entities.iMoveable;
import game.gameboard.Tile;

/**
 * Initiate a move with an actor
 */
public class MoveCommand extends Command {

    private iMoveable actor;     // Actor
    private Tile target;        // Target Tile of move
    private int direction;      // Direction of move

    // Constructor
    public MoveCommand(iMoveable actor, Tile target, int direction, int duration) {
        this.actor = actor;
        this.target = target;
        this.direction = direction;
        super.duration = duration;
    }

    // Get direction of move
    public int getDirection() {
        return this.direction;
    }

    // Execute move from Gameboard function
    public void exec() {}

}
