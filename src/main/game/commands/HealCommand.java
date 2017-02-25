package game.commands;

import game.entities.iHealer;
import game.gameboard.Tile;

// Initiate a heal with an actor
public class HealCommand extends Command {

    private iHealer actor;            // Actor to perform heal
    private Tile target;      // Direction of heal

    // Constructor
    public HealCommand(iHealer actor, Tile target, int duration) {
        this.actor = actor;         // Set actor
        this.target = target;       // Set target tile
        super.duration = duration;  // Set duration to execute
    }

    // Execute heal in Gameboard funcion
    public void exec() {}

}
