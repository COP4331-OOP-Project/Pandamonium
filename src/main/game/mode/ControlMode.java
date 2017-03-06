package game.mode;

import game.GameModel;
import game.commands.CommandEnum;

public class ControlMode {
	Mode currentMode = Mode.RALLY_POINT;
	Submode currentSubmode = Submode.RALLY_POINT;
	GameModel gameModel;
	
	public ControlMode(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public void cycleModeForward() {
		currentMode = currentMode.getNext();
		cycleSubmodeForward();
	}
	
	public void cycleModeBackward() {
		currentMode = currentMode.getPrevious();
		cycleSubmodeForward();
	}

	public void cycleSubmodeForward() {
		currentSubmode = currentSubmode.getNext(currentMode);
	}

	public void cycleSubmodeBackward() {
		currentSubmode = currentSubmode.getPrevious(currentMode);
	}

	public void cycleCommandForward() {
		// TODO Auto-generated method stub
		
	}

	public void cycleCommandBackward() {
		// TODO Auto-generated method stub
		
	}

	public void cycleTypeInstanceBackward() {
		// TODO Auto-generated method stub
		
	}

	public void cycleTypeInstanceForward() {
		// TODO Auto-generated method stub
		
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
