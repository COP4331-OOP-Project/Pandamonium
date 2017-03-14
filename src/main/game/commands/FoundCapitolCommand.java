package game.commands;

import game.entities.units.Colonist;
import game.gameboard.Tile;

// Command to found a capitol using a colonist
public class FoundCapitolCommand extends Command {

	private Colonist actor;	// Colonist to found capital
	private Tile target;	// Target tile to found capitol on

	// Constructor
	public FoundCapitolCommand(Colonist actor, Tile target, int duration) {

		super(duration);
		this.actor = actor;		// Set actor
		this.target = target;	// Set target

	}

	// Execute command
	public void exec() {

	}

}
