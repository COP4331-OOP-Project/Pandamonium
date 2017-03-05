package game.gameboard;

import java.awt.Point;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.Player;
import game.entities.structures.ObservationTower;
import game.entities.structures.Structure;
import game.entities.structures.StructureType;
import game.entities.units.Unit;
import view.game.drawers.UnitDrawer;

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
		setVisible(tower.getLocation(), visibility, tiles); 
	}

	private static void setSurroundingVisible(Unit unit, SimpleTile[][] tiles) {
		int visibility = 1; //This needs to be changed to units actual visibility
		setVisible(unit.getLocation(), visibility, tiles); 
	}
	
	//This function recursively draws the visibility based on the radius and location
	//of a unit or tower 
	private static void setVisible(Location loc, int radius, SimpleTile[][] tiles) {
		if (radius < 0) {
			log.error("");
		}
		int x = loc.getX();
		int y = loc.getY();
		if (radius == 0) {
			if (x >= 0 && y >= 0 && x < tiles.length && y < tiles.length) {
				tiles[x][y].setVisible();
			}
		} else {
			setVisible(new Location(x, y - 1), radius - 1, tiles);
			setVisible(new Location(x + 1, y - 1), radius - 1, tiles);
			setVisible(new Location(x + 1, y), radius - 1, tiles);
			setVisible(new Location(x, y + 1), radius - 1, tiles);
			setVisible(new Location(x - 1, y + 1), radius - 1, tiles);
			setVisible(new Location(x - 1, y), radius - 1, tiles);
		}
	}

}
