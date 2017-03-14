package game.commands;

import game.entities.structures.Structure;
import game.gameboard.Tile;

public class AssignWorkerCommand extends Command{
	private Structure actor;
	private Tile target;
	
	public AssignWorkerCommand(Structure actor, Tile target, int duration) {
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
        super.duration = duration;          // Set duration until assign worker executes
    }
	
	public void exec() {}

}
