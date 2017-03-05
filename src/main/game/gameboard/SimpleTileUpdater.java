package game.gameboard;

import java.util.ArrayList;

import game.Player;
import game.entities.structures.Structure;
import game.entities.structures.StructureType;
import game.entities.units.Unit;

public final class SimpleTileUpdater {
	public static SimpleTile[][] updateTiles(Tile[][] tiles, SimpleTile[][] simpleTiles, Player player) {
		ArrayList<Structure> playerStructures = player.getStructures();
		ArrayList<Unit> playerUnits = player.getAllUnit();
		for (int i = 0; i < simpleTiles.length; i++) {
			for (int j = 0; j < simpleTiles[i].length; j++) {
				simpleTiles[i][j].setSemiIfVisible();
			}
		}
		for (Unit unit: playerUnits) {
			setSurroundingVisible(unit);
		}
		for (Structure structure: playerStructures) {
			if (structure.getType() == StructureType.OBSERVE) {
				setSurroundingVisible(structure);
			}
		}
		return simpleTiles;
	}

	private static void setSurroundingVisible(Structure structure) {
		// TODO Auto-generated method stub
		
	}

	private static void setSurroundingVisible(Unit unit) {
		// TODO Auto-generated method stub
		
	}

}
