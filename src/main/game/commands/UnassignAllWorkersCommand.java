package game.commands;

import game.entities.structures.Structure;
import game.gameboard.Tile;

public class UnassignAllWorkersCommand extends Command{
	private Structure actor;
	private Tile target;
	
	public UnassignAllWorkersCommand(Structure actor, Tile target, int duration) {
		super(duration);
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
    }
	
	public void exec() {}

}