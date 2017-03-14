package game.mode;

import controls.KeyEventController;
import game.GameModel;
import game.Player;
import game.commands.CommandEnum;
import game.commands.managers.CommandManager;
import game.entities.EntityId;
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
		
	}
	
	public void cycleModeBackward() {
		currentMode = currentMode.getPrevious();
		cycleSubmodeForward();
		selectedManager.cycle(false);
	}

	public void cycleSubmodeForward() {
		currentSubmode = currentSubmode.getNext(currentMode);
		selectedManager.cycle(true);
	}

	public void cycleSubmodeBackward() {
		currentSubmode = currentSubmode.getPrevious(currentMode);
		selectedManager.cycle(false);
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
}
