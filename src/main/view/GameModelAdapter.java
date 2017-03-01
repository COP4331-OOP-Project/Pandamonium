package view;

import game.GameModel;
import game.gameboard.SimpleTile;

public class GameModelAdapter {
	GameModel gameModel;
	
	public GameModelAdapter(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public void startGame() {
		gameModel.initializeGame();
	}
	
	public SimpleTile[][] getCurrentTiles() {
		return null;
		
	}
	
}
