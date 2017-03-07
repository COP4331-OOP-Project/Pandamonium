package game.mode;

import game.GameModel;
import game.Player;
import game.commands.CommandEnum;

public class ModeController {
	private Mode currentMode = Mode.RALLY_POINT;
	private Submode currentSubmode = Submode.RALLY_POINT;
	private SelectedEntityManager selectedManager;
	private GameModel gameModel;
	private Player currentPlayer;
	
	public ModeController(GameModel gameModel) {
		this.gameModel = gameModel;
		this.selectedManager = new SelectedEntityManager(gameModel, this);
		currentPlayer = gameModel.getCurrentPlayer();
	}

	public void update() {
		if (gameModel.getCurrentPlayer() != currentPlayer) {
			currentPlayer = gameModel.getCurrentPlayer();
			currentMode = Mode.RALLY_POINT;
			currentSubmode = Submode.RALLY_POINT;
			selectedManager.newPlayer();
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
		// TODO Auto-generated method stub
		
	}

	public void cycleCommandBackward() {
		// TODO Auto-generated method stub
		
	}

	public void cycleEntityBackward() {
		selectedManager.cycle(false);
	}

	public void cycleEntityForward() {
		selectedManager.cycle(true);
	}

	public CommandEnum executeCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	public void centerOnCurrentTypeInstance() {
		// TODO Auto-generated method stub
		
	}

	public void endTurn() {
		gameModel.endTurn();
	}

	public void addMoveToList(int degrees) {
		// TODO Auto-generated method stub
		
	}

	public void executeMoveCommand() {
		// TODO Auto-generated method stub
		
	}

	public void cycleMakeOptionUp() {
		// TODO Auto-generated method stub
		
	}

	public void cycleMakeOptionDown() {
		// TODO Auto-generated method stub
		
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
}
