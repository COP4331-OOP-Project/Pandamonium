package game.mode;

import java.util.ArrayList;

import controls.KeyEventController;
import game.GameModel;
import game.Player;
import game.commands.CommandEnum;
import game.commands.CommandManager;
import game.commands.SubCommandEnum;
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
	private boolean expectingSubCommand = false;
	
	public ModeController(GameModel gameModel) {
		this.gameModel = gameModel;
		this.selectedManager = new SelectedEntityManager(gameModel, this);
		currentPlayer = gameModel.getCurrentPlayer();
		commandManager = new CommandManager(currentPlayer);
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
		clearExpectingSubCommand();
	}
	
	public void cycleModeBackward() {
		currentMode = currentMode.getPrevious();
		cycleSubmodeForward();
		selectedManager.cycle(false);
		updateSelectedEntityCommands();
		clearExpectingSubCommand();
	}

	public void cycleSubmodeForward() {
		currentSubmode = currentSubmode.getNext(currentMode);
		selectedManager.cycle(true);
		updateSelectedEntityCommands();
		clearExpectingSubCommand();
	}

	public void cycleSubmodeBackward() {
		currentSubmode = currentSubmode.getPrevious(currentMode);
		selectedManager.cycle(false);
		updateSelectedEntityCommands();
		clearExpectingSubCommand();
	}

	public void setExpectingSubCommand() { this.expectingSubCommand = true; }
	public void clearExpectingSubCommand() { this.expectingSubCommand = false; }
	public boolean isExpectingSubCommand() { return this.expectingSubCommand; }
	
	public void cycleCommandForward() {
		commandManager.cycleForward();
	}

	public void cycleCommandBackward() {
		commandManager.cycleBackward();
	}

	public ArrayList<CommandEnum> getCommands() {
		return commandManager.getPossibleCommands();
	}

	public ArrayList<SubCommandEnum> getSubCommands() {
		return commandManager.getPossibleSubCommands();
	}

	public CommandEnum getSelectedCommand() {
		return commandManager.getCurrentCommand();
	}

	public SubCommandEnum getSelectedSubCommand() {
		return commandManager.getCurrentSubCommand();
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

		CommandEnum cmd = commandManager.getCurrentCommand();

		switch (cmd) {
			case CREATE_SOLDIERS:
			case ASSIGN_WORKER:
				setExpectingSubCommand();
				break;
			default:
				clearExpectingSubCommand();
				commandManager.execute(selectedManager.getSelectedEntity());
		}

	}

	public void executeSubCommand() {

		commandManager.executeSubCommand(selectedManager.getSelectedEntity());

	}

	public void setCommand(CommandEnum command) {
		commandManager.setCommand(command);
	}

	public void setSubCommand(SubCommandEnum command) {
		commandManager.setSubCommand(command);
	}
}
