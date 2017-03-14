package game.commands;

import game.entities.structures.Structure;
import game.gameboard.Tile;

public class WorkerFarmCommand extends Command{
	private Structure actor;
	private Tile target;
	
	public WorkerFarmCommand(Structure actor, Tile target, int duration) {
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
        super.duration = duration;          // Set duration until assign worker executes
    }
	
	public void exec() {}
}