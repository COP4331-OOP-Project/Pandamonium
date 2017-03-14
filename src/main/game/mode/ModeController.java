package game.mode;

import java.util.ArrayList;

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
			selectedManager.togglePlayer();
			keyEventController.togglePlayer();
		}
	}
	
	public void cycleModeForward() {
		currentMode = currentMode.getNext();
		cycleSubmodeForward();
		selectedManager.cycle(true);
		updateSelectedEntityCommands();
	}
	
	public void cycleModeBackward() {
		currentMode = currentMode.getPrevious();
		cycleSubmodeForward();
		selectedManager.cycle(false);
		updateSelectedEntityCommands();
	}

	public void cycleSubmodeForward() {
		currentSubmode = currentSubmode.getNext(currentMode);
		selectedManager.cycle(true);
		updateSelectedEntityCommands();
	}

	public void cycleSubmodeBackward() {
		currentSubmode = currentSubmode.getPrevious(currentMode);
		selectedManager.cycle(false);
		updateSelectedEntityCommands();
	}
	
	public void cycleCommandForward() {
		commandManager.cycleForward();
	}

	public void cycleCommandBackward() {
		commandManager.cycleBackward();
	}
	
	public void executeCommand(CommandEnum command) {
		commandManager.executeCommand(command);
	}
	
	public ArrayList<CommandEnum> getCommands() {
		return commandManager.getPossibleCommands();
	}
	
	public CommandEnum getSelectedCommand() {
		return commandManager.getCurrentCommand();
	}

	public void addMoveToList(int degrees) {
		commandManager.addMoveToList(degrees);
	}

	public void cycleEntityBackward() {
		selectedManager.cycle(false);
		updateSelectedEntityCommands();
	}

	public void cycleEntityForward() {
		selectedManager.cycle(true);
		updateSelectedEntityCommands();
	}
	
	public void updateSelectedEntityCommands() {
		EntityId selected = selectedManager.getSelectedEntity();
		if (selected != null) {
			iCommandable selectedCommandable = commandableFromEntityId(selected);
			commandManager.updateSelectedEntity(selectedCommandable);
		}
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
	}

	public void execute() {
		commandManager.execute();
	}
}
