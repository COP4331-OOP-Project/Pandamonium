package game.gameboard;

import game.Player;
import game.entities.EntitySubtypeEnum;
import game.entities.structures.Structure;
import game.entities.units.Explorer;
import game.entities.units.Unit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public final class SimpleTileUpdater {

    private final static Logger log = LogManager.getLogger(SimpleTileUpdater.class);
	public static SimpleTile[][] updateTiles(Tile[][] tiles, SimpleTile[][] simpleTiles, Player player) {
		ArrayList<Structure> playerStructures = player.getStructures();
		ArrayList<Unit> playerUnits = player.getUnits();
		for (Unit unit : playerUnits) {
			if (unit.getType() == EntitySubtypeEnum.EXPLORER) {
				Explorer explorer = (Explorer)unit;
				if (explorer.getProspecting()) {
					simpleTiles[explorer.getLocationX()][explorer.getLocationY()].setProspected();
				}
			}
		}
		for (int i = 0; i < simpleTiles.length; i++) {
			for (int j = 0; j < simpleTiles[i].length; j++) {
				simpleTiles[i][j].setSemiIfVisible();
			}
		}
		for (Unit unit: playerUnits) {
			setSurroundingVisible(unit, simpleTiles);
		}
		for (Structure structure: playerStructures) {
			if (structure.getType() == EntitySubtypeEnum.OBSERVE) {
				setSurroundingVisible(structure, simpleTiles);
			}
		}
		return simpleTiles;
	}


	private static void setSurroundingVisible(Structure structure, SimpleTile[][] tiles) {
		updateVisiblties(structure.getLocation(), structure.getStats().getInfluence(), tiles); 
	}

	private static void setSurroundingVisible(Unit unit, SimpleTile[][] tiles) {
		updateVisiblties(unit.getLocation(), unit.getStats().getInfluence(), tiles); 
	}
	 
	private static void updateVisiblties(Location loc, int radius, SimpleTile[][] tiles) {
		if (radius < 0) {
			log.error("");
		}
		int x = loc.getX();
		int y = loc.getY();
		if (x >= 0 && y >= 0 && x < tiles.length && y < tiles.length) {
			tiles[x][y].setVisible(); //Make the actual tile visible
			for (int i = 1; i <= radius; i++) {
				setVisible(x, y - i, tiles);
				setVisible(x, y + i, tiles);
				setVisible(x - i, y, tiles);
				setVisible(x + i, y, tiles);
				setVisible(x - i, y + i, tiles);
				setVisible(x + i, y - i, tiles);
				for (int j = 1; j <= radius; j++) {

					setVisible(x + i, y - j, tiles);
					setVisible(x + j, y - i, tiles);
					setVisible(x - i, y + j, tiles);
					setVisible(x - j, y + i, tiles);
					if (i + j <= radius) {
						setVisible(x + j, y + i, tiles);
						setVisible(x - j, y - i, tiles);
					}
				}
			}
		}
	}
	
	private static void setVisible(int x, int y, SimpleTile[][] tiles) {
		if (x >= 0 && y >= 0 && x < tiles.length && y < tiles.length && tiles[x][y].getTileType() != TerrainEnum.NON_TILE) {
			tiles[x][y].setVisible();
		}
	}

}
