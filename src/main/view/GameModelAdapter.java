package view;

import game.GameModel;
import game.gameboard.SimpleTile;
import game.mode.ModeController;
import game.mode.Mode;
import game.mode.Submode;

public class GameModelAdapter {
	GameModel gameModel;
	ModeController controlMode;
	
	public GameModelAdapter(GameModel gameModel, ModeController controlMode) {
		this.gameModel = gameModel;
		this.controlMode = controlMode;
	}

	public void startGame() {
		gameModel.initializeGame();
	}
	
	public SimpleTile[][] getCurrentTiles() {
		return gameModel.getCurrentPlayer().getSimpleTiles();
	}

	public Mode getCurrentMode() {
		return controlMode.getGameMode();
	}

	public Submode getCurrentSubmode() {
		return controlMode.getGameSubmode();
	}

	public void setMode(Mode mode) {
		controlMode.setMode(mode);
	}
	
	public void setSubmode(Submode submode) {
		controlMode.setSubmode(submode);
	}

	public int getPlayer() {
		if (gameModel.getCurrentPlayer() == gameModel.getPlayer(0)) {
			return 0;
		} else {
			return 1;
		}
	}

	public int getTurnNum() {
		return gameModel.getTurnNum();
	}

	//Yes I know these violate LoD
	public int getCurrentFood() {
		return (int)gameModel.getCurrentPlayer().getMetal().getAmount();
	}
	
	public int getCurrentMetal() {
		return (int)gameModel.getCurrentPlayer().getMetal().getAmount();
	}
	
	public int getCurrentPower() {
		return (int)gameModel.getCurrentPlayer().getPower().getAmount();
	}
	
}
