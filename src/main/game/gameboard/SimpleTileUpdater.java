package game.gameboard;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import game.Player;
import game.entities.structures.ObservationTower;
import game.entities.structures.Structure;
import game.entities.structures.StructureType;
import game.entities.units.Unit;

public final class SimpleTileUpdater {

    private final static Logger log = LogManager.getLogger(SimpleTileUpdater.class);
	public static SimpleTile[][] updateTiles(Tile[][] tiles, SimpleTile[][] simpleTiles, Player player) {
		ArrayList<Structure> playerStructures = player.getStructures();
		ArrayList<Unit> playerUnits = player.getAllUnit();
		for (int i = 0; i < simpleTiles.length; i++) {
			for (int j = 0; j < simpleTiles[i].length; j++) {
				simpleTiles[i][j].setSemiIfVisible();
			}
		}
		for (Unit unit: playerUnits) {
			setSurroundingVisible(unit, simpleTiles);
		}
		for (Structure structure: playerStructures) {
			if (structure.getType() == StructureType.OBSERVE) {
				setSurroundingVisible(structure, simpleTiles);
			}
		}
		return simpleTiles;
	}


	private static void setSurroundingVisible(Structure structure, SimpleTile[][] tiles) {
		ObservationTower tower = (ObservationTower) structure;
		int visibility = 1; //This needs to be changed to units actual visibility
		updateVisiblties(tower.getLocation(), visibility, tiles); 
	}

	private static void setSurroundingVisible(Unit unit, SimpleTile[][] tiles) {
		int visibility = 1; //This needs to be changed to units actual visibility
		updateVisiblties(unit.getLocation(), visibility, tiles); 
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
