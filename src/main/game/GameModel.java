package game;

import java.util.ArrayList;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.UnitFactory;
import game.entities.factories.exceptions.ColonistLimitExceededException;
import game.entities.factories.exceptions.ExplorerLimitExceededException;
import game.entities.factories.exceptions.MeleeLimitExceededException;
import game.entities.factories.exceptions.RangedLimitExceededException;
import game.entities.stats.UnitStats;
import game.entities.units.Colonist;
import game.entities.units.exceptions.UnitNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.commands.MoveCommand;
import game.gameboard.Gameboard;
import game.gameboard.Location;

public class GameModel {
	private static final Location HUMAN_STARTING_LOCATION = new Location(5, 28);
	private static final Location PANDA_STARTING_LOCATION = new Location(32, 11);
    private final static Logger log = LogManager.getLogger(GameModel.class);
    private Player currentPlayer;
    private Gameboard gBoard;
    private ArrayList<Player> players;
    private ArrayList<MoveCommand> moveCommands = new ArrayList<MoveCommand>();
    private ArrayList<Location> moveLocations = new ArrayList<Location>();
    private int turnNum = 0;
    private Location lastMoveLocation;
    private boolean gameHasStarted = false;
    
    public void initializeGame() {
        try {
            this.players = new ArrayList<Player>();
            Player human = new Player(0, HUMAN_STARTING_LOCATION);
            Player panda = new Player(1, PANDA_STARTING_LOCATION);
            currentPlayer = human;
            players.add(human);
            players.add(panda);
            gBoard = new Gameboard(players);
            initialUnit(human, panda);
            human.initializeSimpleTiles(gBoard.getTiles());
            panda.initializeSimpleTiles(gBoard.getTiles());
            human.updateSimpleTiles(gBoard.getTiles());
            panda.updateSimpleTiles(gBoard.getTiles());
            gameHasStarted = true;
            startTurn();
        }catch(GameFailedToStartException e){
            System.out.println(e.getMessage());
        }

    }
    
    public void updateGame() { //This is called up to 60 times per second
    	if (gameHasStarted) {
    		checkIfGameOver();
    	}
    }

    public void initialUnit(Player human, Player panda) throws GameFailedToStartException {
        try {
            UnitFactory unitFactory = new UnitFactory();

            Colonist humanColnist = (Colonist)unitFactory.createUnit(EntitySubtypeEnum.COLONIST,HUMAN_STARTING_LOCATION, 0);
            Colonist pandaColonist = (Colonist)unitFactory.createUnit(EntitySubtypeEnum.COLONIST, PANDA_STARTING_LOCATION,1);

            human.addColonist(humanColnist);
            panda.addColonist(pandaColonist);

            gBoard.addUnitToTile(humanColnist);
            gBoard.addUnitToTile(pandaColonist);
        }catch(UnitNotFoundException |ColonistLimitExceededException |ExplorerLimitExceededException| MeleeLimitExceededException
                |RangedLimitExceededException e){
            throw new GameFailedToStartException();
        }
    }
    
    private void checkIfGameOver() {
	}
    
    public void startTurn() {
    	if (currentPlayer == players.get(0)) {
    		turnNum++;
    	}
    	currentPlayer.updateSimpleTiles(gBoard.getTiles());
    }
    
    public void endTurn() {
    	currentPlayer.updateSimpleTiles(gBoard.getTiles());
    	if (currentPlayer == players.get(0)) {
    		currentPlayer = players.get(1);
    	} else {
    		currentPlayer = players.get(0);
    	}
    	startTurn();
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
}
