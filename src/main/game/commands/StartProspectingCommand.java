package game.commands;

import game.entities.units.Explorer;
import game.gameboard.Tile;

public class StartProspectingCommand extends Command{
	private Explorer actor;
	private Tile target;
	
	public StartProspectingCommand(Explorer actor, Tile target, int duration) {
		super(duration);
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
    }
	
	public void exec() {
		actor.startProspecting();
	}
}
