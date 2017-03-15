package game.commands;

import game.entities.structures.Structure;
import game.entities.structures.StructureWithWorker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Tile;

// Command to assign a worker on a structure to gather on a tile within its influence radius
public class AssignWorkerCommand extends Command {

	private Structure actor;				// Structure to assign worker to
	private Tile target;	    			// Target tile to set worker on
	private WorkerTypeEnum type;			// Job to assign workers to
	private int numberOfWorkersAssigned;	// Number of workers to assign
	
	public AssignWorkerCommand(StructureWithWorker actor, Tile target, int duration,
							   WorkerTypeEnum type, int numberOfWorkersAssigned) {

		super(duration);					// Duration command takes
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
		this.type = type;
		this.numberOfWorkersAssigned = numberOfWorkersAssigned;

    }

    // Execute command
	public void exec() {
//		actor.
	}

}
