package game.mode;

import controls.KeyEventController;
import game.GameModel;
import game.Player;
import game.commands.CommandEnum;
import game.commands.CommandManager;
import game.commands.iCommandable;
import game.entities.EntityId;
import game.entities.EntityTypeEnum;
import game.entities.managers.exceptions.ArmyDoesNotExistException;
import game.entities.managers.exceptions.RallyPointDoesNotExistException;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.gameboard.Location;

public class ModeController {
	private Mode currentMode = Mode.RALLY_POINT;
	private Submode currentSubmode = Submode.RALLY_POINT;
	private SelectedEntityManager selectedManager;
	private CommandManager commandManager;
	private GameModel gameModel;
	private Player currentPlayer;
	private KeyEventController keyEventController;
	
	public ModeController(GameModel gameModel) {
		this.gameModel = gameModel;
		this.selectedManager = new SelectedEntityManager(gameModel, this);
		currentPlayer = gameModel.getCurrentPlayer();
		commandManager = new CommandManager();
	}

	public void update() {
		if (gameModel.getCurrentPlayer() != currentPlayer) {
			currentPlayer = gameModel.getCurrentPlayer();
			commandManager.setPlayer(currentPlayer);
			currentMode = Mode.RALLY_POINT;
			currentSubmode = Submode.RALLY_POINT;
			selectedManager.newPlayer();
			keyEventController.togglePlayer();
		}
	}
	
	public void cycleModeForward() {
		currentMode = currentMode.getNext();
		cycleSubmodeForward();
		selectedManager.cycle(true);
		commandManager.updateSelectedEntity(commandableFromEntityId(selectedManager.getSelectedEntity()));
	}
	
	public void cycleModeBackward() {
		currentMode = currentMode.getPrevious();
		cycleSubmodeForward();
		selectedManager.cycle(false);
		commandManager.updateSelectedEntity(commandableFromEntityId(selectedManager.getSelectedEntity()));
	}

	public void cycleSubmodeForward() {
		currentSubmode = currentSubmode.getNext(currentMode);
		selectedManager.cycle(true);
		commandManager.updateSelectedEntity(commandableFromEntityId(selectedManager.getSelectedEntity()));
	}

	public void cycleSubmodeBackward() {
		currentSubmode = currentSubmode.getPrevious(currentMode);
		selectedManager.cycle(false);
		commandManager.updateSelectedEntity(commandableFromEntityId(selectedManager.getSelectedEntity()));
	}
	
	public void cycleCommandForward() {
		commandManager.cycleForward();
	}

	public void cycleCommandBackward() {
		commandManager.cycleBackward();
	}
	
	public void executeCommand() {
		commandManager.execute();
	}

	public void addMoveToList(int degrees) {
		commandManager.addMoveToList(degrees);
	}

	public void cycleEntityBackward() {
		selectedManager.cycle(false);
	}

	public void cycleEntityForward() {
		selectedManager.cycle(true);
	}

	public void endTurn() {
		gameModel.endTurn();
		commandManager.setPlayer(currentPlayer);
	}
	
	public Mode getGameMode() {
		return currentMode;
	}
	
	public Submode getGameSubmode() {
		return currentSubmode;
	}

	public void setMode(Mode mode) {
		currentMode = mode;
		cycleSubmodeForward();
	}

	public void setSubmode(Submode submode) {
		currentSubmode = submode;
	}

	public EntityId getSelectedEntity() {
		return selectedManager.getSelectedEntity();
	}
	
	public Location getSelectedLocation() {
		return selectedManager.getSelectedLocation();
	}
	
	public void setKeyEventController(KeyEventController event) {
		keyEventController = event;
	}
	
	private iCommandable commandableFromEntityId(EntityId entityId) {
		if (entityId != null) {
			try {
				switch (entityId.getTypeId()) {
					case RALLYPOINT:
						return currentPlayer.getRallyPoint(entityId);
					case STRUCTURE:
						return currentPlayer.getStructure(entityId);
					case UNIT:
						return currentPlayer.getUnit(entityId);
					case ARMY:
						return currentPlayer.getArmy(entityId);
					default:
						return null;
				} 
			} catch (RallyPointDoesNotExistException | StructureDoesNotExistException |
					UnitDoesNotExistException | ArmyDoesNotExistException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
}
