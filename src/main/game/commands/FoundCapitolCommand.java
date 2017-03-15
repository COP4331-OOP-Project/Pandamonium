package game.commands;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.entities.managers.WorkerManager;
import game.entities.units.Colonist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;

// Command to found a capitol using a colonist
public class FoundCapitolCommand extends Command {

	private Colonist actor;							// Colonist to found capital
	private Location location;						// Target location to found capitol on
	private StructureManager structureManager;		// Structure manager to create structure
	private UnitManager unitManager;				// Unit manager to create melee units
	private WorkerManager workerManager;			// Worker manager to create workers

	// Constructor
	public FoundCapitolCommand(Colonist actor, Location location, int duration, StructureManager structureManager) {

		super(duration);
		this.actor = actor;							// Set actor
		this.location = location;						// Set location
		this.structureManager = structureManager;	// Set structure manager

	}

	// Execute command
	public void exec() {

		// Create Capitol and starting units and add it to location tile
		try {
			this.structureManager.addStructure(EntitySubtypeEnum.CAPITOL, this.location);
			this.unitManager.addUnit(EntitySubtypeEnum.MELEE, this.location);
			this.unitManager.addUnit(EntitySubtypeEnum.MELEE, this.location);
			this.workerManager.addWorker(WorkerTypeEnum.STANDBY, this.location);
			this.workerManager.addWorker(WorkerTypeEnum.STANDBY, this.location);
			this.workerManager.addWorker(WorkerTypeEnum.STANDBY, this.location);
			this.workerManager.addWorker(WorkerTypeEnum.STANDBY, this.location);
			this.workerManager.addWorker(WorkerTypeEnum.STANDBY, this.location);
			this.actor.decommissionEntity();	// Decommission colonist now that capitol exists
		} catch (Exception e) {
			e.getLocalizedMessage();
		}

	}

}
