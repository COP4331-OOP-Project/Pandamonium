package game.gameboard;

import game.entities.BattleGroup;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.areaEffects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Gameboard {
	private static final int BOARD_SIZE = 42;
	private static final File MAP_FILE = new File("assets/maps/default.map");
    private final static Logger log = LogManager.getLogger(Gameboard.class);
    private Tile[][] board;     // Map for game tiles  // 0 is grass, 1 is sand, 2 is water
    //private ArrayList<Player> players;              // Players for game
	
	public Gameboard(/*ArrayList<Player> players*/) {
        //this.players = players;                     // Set players
        setupMap();                                 // Setup board
	}
    
    private void setupMap() {
    	int[][] map = MapLoader.getMap(BOARD_SIZE, MAP_FILE);
        board = new Tile[BOARD_SIZE][];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = new Tile[BOARD_SIZE];
            for (int j = 0; j < BOARD_SIZE; j++) {
                Location l = new Location(i, j);
                if (map[i][j] == -1)
                    board[i][j] = new Tile(TerrainEnum.NON_TILE, l, null);
                if (map[i][j] == 0)
                    board[i][j] = new Tile(TerrainEnum.GRASS, l, null);
                if (map[i][j] == 1)
                    board[i][j] = new Tile(TerrainEnum.SAND, l, null);
                if (map[i][j] == 2)
                    board[i][j] = new Tile(TerrainEnum.WATER, l, null);
                if (map[i][j] == 3) {
                    board[i][j] = new Tile(TerrainEnum.MOUNTAIN, l, null);
                }
            }
        }

        this.addSeeminglyRandomAreaEffects();
    }
    
    public Tile getTileWithLocation(Location l) {
        return board[l.getX()][l.getY()];
    }
    
    public Tile[][] getTiles() {
		return board;
    }

    public void addUnitToTile(Unit unit, Location location){
	    board[location.getX()][location.getY()].addUnit(unit);
    }
    
    public void addStructureToTile(Structure structure, Location location){
	    board[location.getX()][location.getY()].addStructure(structure);
    }

    public void addArmyToTile(BattleGroup battleGroup, Location location){
        board[location.getX()][location.getY()].addBattleGroup(battleGroup);
    }

    public void addRallyPoinTToTile(RallyPoint rallyPoint, Location location){
        board[location.getX()][location.getY()].addRallyPoint(rallyPoint);
    }

    public void addAreaEffectToTile(AreaEffect areaEffect, Location location) throws AreaEffectAlreadyPresentException {
        board[location.getX()][location.getY()].addAreaEffect(areaEffect);
    }

    private void addSeeminglyRandomAreaEffects() {
        // 5,28
        // 32,11
        AreaEffect lowHealEffect = new HealAreaEffect(2);
        AreaEffect medHealEffect = new HealAreaEffect(6);
        AreaEffect highHealEffect = new HealAreaEffect(10);

        AreaEffect lowDamageEffect = new DamageAreaEffect(2);
        AreaEffect medDamageEffect = new DamageAreaEffect(6);
        AreaEffect highDamageEffect = new DamageAreaEffect(10);

        AreaEffect instantDeathEffect = new InstantDeathAreaEffect();


        addAreaEffectLogException(this.board[6][30], lowDamageEffect);
        //TODO: add some area effects
    }

    private void addAreaEffectLogException(Tile t, AreaEffect areaEffect) {
        if (t.isImpassable()) return;

        try {
            t.addAreaEffect(areaEffect);
        } catch (AreaEffectAlreadyPresentException e) {
            log.warn("Tried to add area effect to tile that already contains one on map initialization.");
        }
    }
}
