package game.commands;

import game.Player;
import game.entities.structures.StructureWithWorker;

public class WorkerGenerateCommand extends Command{
	private StructureWithWorker actor;
	private Player player;
	
	public WorkerGenerateCommand(StructureWithWorker actor, Player player, int duration) {
		super(duration);
        this.actor = actor;                 // Set attacker
        this.player = player;               // Set Player
    }
	
	public void exec() {
		this.player.addPower(actor.harvestPower());
	}
}