package game.commands;

import game.Player;
import game.entities.structures.Structure;
import game.entities.structures.StructureWithWorker;
import game.gameboard.Tile;

public class WorkerMineCommand extends Command{
	private StructureWithWorker actor;
	private Player player;
	
	public WorkerMineCommand(StructureWithWorker actor, Player player, int duration) {
		super(duration);
        this.actor = actor;                 // Set attacker
		this.player = player;				// Set player
    }
	
	public void exec() {
		this.player.addMetal(this.actor.harvestMetal());
	}
}