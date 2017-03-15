package game.commands;

import game.Player;
import game.entities.structures.StructureWithWorker;
import game.resources.Resource;

import java.util.ArrayList;

public class WorkerFarmCommand extends Command{
	private StructureWithWorker actor;
	private Player player;

	public WorkerFarmCommand(StructureWithWorker actor, Player player, int duration) {
		super(duration);
        this.actor = actor;                 // Set attacker
		this.player = player;				// Set player, need it to add resources
    }
	
	public void exec() {
		this.player.addNutrients(actor.harvestNutrients());
	}
}