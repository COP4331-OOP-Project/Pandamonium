package game.gameboard;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.Player;

public class Gameboard {
	private static final int BOARD_SIZE = 42;
	private static final File MAP_FILE = new File("assets/maps/default.map");
    private final static Logger log = LogManager.getLogger(Gameboard.class);
    private Tile[][] board;     // Map for game tiles  // 0 is grass, 1 is sand, 2 is water
    private ArrayList<Player> players;              // Players for game
	
	public Gameboard(ArrayList<Player> players) {
        this.players = players;                     // Set players
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
                    board[i][j] = new Tile(TerrainEnum.NON_TILE, l);
                if (map[i][j] == 0)
                    board[i][j] = new Tile(TerrainEnum.GRASS, l);
                if (map[i][j] == 1)
                    board[i][j] = new Tile(TerrainEnum.SAND, l);
                if (map[i][j] == 2)
                    board[i][j] = new Tile(TerrainEnum.WATER, l);
                if (map[i][j] == 3) {
                    board[i][j] = new Tile(TerrainEnum.MOUNTAIN, l);
                }
            }
        }
    }
    
    public Tile getTileWithLocation(Location l) {
        return board[l.getX()][l.getY()];
    }
    
    public Tile[][] getTiles() {
		return board;
    }
}
