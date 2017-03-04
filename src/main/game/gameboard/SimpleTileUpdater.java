package game.gameboard;

public final class SimpleTileUpdater {
	public static SimpleTile[][] updateTiles(Tile[][] tiles, SimpleTile[][] simpleTiles) {
		for (int i = 0; i < simpleTiles.length; i++) {
			for (int j = 0; j < simpleTiles[i].length; j++) {
				//simpleTiles[i][j].setSemiIfVisible();
				simpleTiles[i][j].setVisible();
			}
		}
		return simpleTiles;
	}

}
