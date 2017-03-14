package game.commands;

import game.entities.structures.Structure;
import game.gameboard.Tile;

// Command to assign a worker on a structure to gather on a tile
public class AssignWorkerCommand extends Command {

	private Structure actor;	// Structure to assign worker to
	private Tile target;	    // Target tile to set worker on
	
	public AssignWorkerCommand(Structure actor, Tile target, int duration) {

		super(duration);					// Duration command takes
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target

    }

    // Execute command
	public void exec() {

	}

}
