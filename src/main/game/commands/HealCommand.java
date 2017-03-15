package game.commands;

import game.entities.iHealer;
import game.gameboard.Tile;
import game.visitors.HealVisitor;

// Initiate a heal with an actor
public class HealCommand extends Command {

    private iHealer actor;    // Actor to perform heal
    private Tile target;      // Target tile to heal

    // Constructor
    public HealCommand(iHealer actor, Tile target, int duration) {

    	super(duration);
        this.actor = actor;         // Set actor
        this.target = target;       // Set target tile

    }

    // Execute command
    public void exec() {
        target.accept(new HealVisitor(actor.getHealing()));
    }

}
