package game.commands;

import game.Player;
import game.entities.*;
import game.entities.managers.*;
import game.entities.managers.exceptions.RallyPointDoesNotExistException;
import game.entities.units.Colonist;
import game.entities.units.exceptions.UnitNotFoundException;
public class CommandExecutor {

	private CommandDispatcher dispatcher;
	private StructureManager structureManager;
	private UnitManager unitManager;
	private WorkerManager workerManager;
	private ArmyManager armyManager;
	private PlacementManager placementManager;

	public CommandExecutor(StructureManager structureManager, UnitManager unitManager,
						   WorkerManager workerManager, ArmyManager armyManager, PlacementManager placementManager) {
		this.structureManager = structureManager;
		this.unitManager = unitManager;
		this.workerManager = workerManager;
		this.armyManager = armyManager;
		this.dispatcher = new CommandDispatcher(unitManager, structureManager, armyManager, placementManager, workerManager);
		this.placementManager = placementManager;

	}

	public void executeSubCommand(EntityId selectedEntity, SubCommandEnum selectedCommand, Player currentPlayer) {

		Entity entity = null;

		try {
			switch (selectedCommand) {
				case CREATE_MELEE:
					entity = currentPlayer.getEntityById(selectedEntity);
					dispatcher.issueMakeUnitCommand(entity, entity.getLocation(), EntitySubtypeEnum.MELEE);
			}
		} catch (UnitNotFoundException e) {
			e.getLocalizedMessage();
		}

	}

	public void executeCommand(EntityId selectedEntity, CommandEnum selectedCommand, Player currentPlayer) {
		Entity entity = null;
		RallyPoint rally = null;

		try {
			if (selectedEntity.getTypeId() != EntityTypeEnum.RALLYPOINT) {
				entity = currentPlayer.getEntityById(selectedEntity);
			} else {
				rally = currentPlayer.getRallyPoint(selectedEntity);
			}
			switch (selectedCommand) {
				case MOVE_RALLY_POINT:
					//only case that uses rally
					break;
				case POWER_UP:	
					dispatcher.issuePowerUpCommand(entity);
					break;
				case POWER_DOWN:
					dispatcher.issuePowerDownCommand(entity);
					break;
				case DECOMMISSION:
					dispatcher.issueDecommissionCommand(entity);
					break;
				case CANCEL_QUEUE:
					dispatcher.issueCancelQueueCommand(entity);
					break;
				case MOVE:
					//Need way to get move location first
					break;
				case REINFORCE_ARMY:
					//Need tile selector
					break;
				case ATTACK:
					//Need tile selector
					break;
				case CREATE_SOLDIERS:
					break;
				case ASSIGN_WORKER:
					break;
				case UNASSIGN_ALL_WORKERS:
					break;
				case DEFEND:
					break;
				case HEAL:
					break;
				case FOUND_CAPITOL:
					Colonist colonist = (Colonist)entity;
					dispatcher.issueFoundCapitolCommand(colonist, colonist.getLocation());
					break;
				case START_PROSPECTING:
					break;
				case STOP_PROSPECTING:
					break;
				case BREED_WORKERS:
					break;
				case WORKER_FARM:
					break;
				case WORKER_MINE:
					break;
				case WORKER_GENERATE:
					break;
				case DROP_OFF_WORKER:
					break;
				case PICK_UP_WORKER:
					break;
				case BUILD_STRUCTURE:
					break;
				case DISBAND_ARMY:
					break;
			}
		} catch (UnitNotFoundException | RallyPointDoesNotExistException e) {
			e.printStackTrace();
		}
	}
}
