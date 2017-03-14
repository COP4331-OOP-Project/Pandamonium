package game.commands;

import game.entities.structures.Fort;
import game.gameboard.Tile;

// Command to turn workers on a Fort into Melee units
public class CreateSoldiersCommand extends Command {

	// Constructor
	public CreateSoldiersCommand(Fort actor, Tile target, int duration) {
		super(duration);
	}

	// Execute creation of soldiers
	public void exec() {

	}

}
