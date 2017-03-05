package game.gameboard;

public final class SimpleTileUpdater {
	public static SimpleTile[][] updateTiles(Tile[][] tiles, SimpleTile[][] simpleTiles) {
		for (int i = 0; i < simpleTiles.length; i++) {
			for (int j = 0; j < simpleTiles[i].length; j++) {
				//simpleTiles[i][j].setSemiIfVisible();
				if (i == 5 && j == 28)
					simpleTiles[i][j].setVisible();
				if (i == 5 && j == 27)
					simpleTiles[i][j].setVisible();
				if (i == 6 && j == 27)
					simpleTiles[i][j].setVisible();
				if (i == 6 && j == 28)
					simpleTiles[i][j].setVisible();
				if (i == 5 && j == 29)
					simpleTiles[i][j].setVisible();
				if (i == 4 && j == 29)
					simpleTiles[i][j].setVisible();
				if (i == 4 && j == 28)
					simpleTiles[i][j].setVisible();
				if (j > 30)
					simpleTiles[i][j].setVisible();
			}
		}
		return simpleTiles;
	}

}
