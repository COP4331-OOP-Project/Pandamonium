package game;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.commands.MoveCommand;
import game.entities.EntityId;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import game.mode.ControlMode;

public class GameModel {
	private static final Location HUMAN_STARTING_LOCATION = new Location(5, 28);
	private static final Location PANDA_STARTING_LOCATION = new Location(32, 11);
    private final static Logger log = LogManager.getLogger(GameModel.class);
    private Player currentPlayer;
    private ArrayList<Player> players;
    private Gameboard gBoard;
    private Player nextPlayer;
    private int turnNum;
    private ArrayList<MoveCommand> moveCommands = new ArrayList<MoveCommand>();
    private ArrayList<Location> moveLocations = new ArrayList<Location>();
    private Location lastMoveLocation;
    private EntityId selectedEntity; 
    private boolean gameHasStarted = false;
    
    public void initializeGame() {
        this.players = new ArrayList<Player>();
        Player human = new Player(0, HUMAN_STARTING_LOCATION);
        Player panda = new Player(1, PANDA_STARTING_LOCATION);
        players.add(human);
        players.add(panda);
        gBoard = new Gameboard(players);
        currentPlayer = human;
        nextPlayer = players.get(1);
        turnNum = 1;
        gameHasStarted =  true;
    }
    
    public void updateGame() { //This is called up to 60 times per second
    	if (gameHasStarted) {
    		checkIfGameOver();
    	}
    }
    
    private void checkIfGameOver() {
	}
    
    public void endTurn() {
    }

	public ArrayList<Location> getMoveLocations() {
        return moveLocations;
    }
	
    public int getTurnNum() {
        return turnNum;
    }
    
    public Gameboard getGameboard() {
        return gBoard;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public Player getPlayer(int playerID) {
        return players.get(playerID);
    }
    
    public EntityId getSelectedEntity() {
    	return selectedEntity;
    }
}
