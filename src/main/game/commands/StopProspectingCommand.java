package game.commands;

import game.entities.units.Explorer;
import game.gameboard.Tile;

public class StopProspectingCommand extends Command{
	private Explorer actor;
	private Tile target;
	
	public StopProspectingCommand(Explorer actor, int duration) {
		super(duration);
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
    }
	
	public void exec() {
		actor.stopProspecting();
	}
}
