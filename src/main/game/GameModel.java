package game;

import game.commands.MoveCommand;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.UnitFactory;
import game.entities.factories.exceptions.*;
import game.entities.structures.Capitol;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class GameModel {
	private static final Location HUMAN_STARTING_LOCATION = new Location(5, 28);
	private static final Location PANDA_STARTING_LOCATION = new Location(32, 11);
    private final static Logger log = LogManager.getLogger(GameModel.class);
    private UnitFactory unitFactory;
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
            gBoard = new Gameboard();
            Player human = new Player(0, HUMAN_STARTING_LOCATION, gBoard);
            Player panda = new Player(1, PANDA_STARTING_LOCATION, gBoard);
            currentPlayer = human;
            players.add(human);
            players.add(panda);
            initialUnits(human, panda);
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

    }

    public void initialUnits(Player human, Player panda) throws GameFailedToStartException {
        try {
            Unit unit1 = (Unit) human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, HUMAN_STARTING_LOCATION);
            Unit unit2 = (Unit) panda.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, PANDA_STARTING_LOCATION);
          /*
            Unit unit3 = (Unit) human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.EXPLORER, HUMAN_STARTING_LOCATION);
            
            Unit explorer = (Unit) human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.EXPLORER, HUMAN_STARTING_LOCATION);
            Unit melee = (Unit) human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, new Location (HUMAN_STARTING_LOCATION.getX() + 1, HUMAN_STARTING_LOCATION.getY() + 2));
            Unit ranged = (Unit) human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, new Location (HUMAN_STARTING_LOCATION.getX() + 4, HUMAN_STARTING_LOCATION.getY() + 4));

            
            Structure observationTower = (Structure) human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.OBSERVE, new Location (HUMAN_STARTING_LOCATION.getX() - 2, HUMAN_STARTING_LOCATION.getY() + 1));
            Structure mine = (Structure) human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.MINE, new Location (HUMAN_STARTING_LOCATION.getX(), HUMAN_STARTING_LOCATION.getY() + 1));
            Structure capitol = (Structure) human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, new Location (HUMAN_STARTING_LOCATION.getX() - 1, HUMAN_STARTING_LOCATION.getY()));
            Structure farm1 = (Structure) human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, new Location (HUMAN_STARTING_LOCATION.getX() -1, HUMAN_STARTING_LOCATION.getY() -1));
            Structure farm2 = (Structure) human.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, new Location (HUMAN_STARTING_LOCATION.getX() + 1, HUMAN_STARTING_LOCATION.getY()));
        */
        } catch (EntityTypeDoesNotExistException | UnitTypeDoesNotExistException | StructureTypeDoesNotExist e) {
            log.error("Error initializing game. " + e.getLocalizedMessage());
            throw new GameFailedToStartException();
        } catch (UnitTypeLimitExceededException | StructureTypeLimitExceededException e) {
            log.error("Error initializing game . " + e.getLocalizedMessage());
            throw new GameFailedToStartException();
        } catch (TotalUnitLimitExceededException | TotalStructureLimitExceededException e) {
            log.error("Error initializing game.  " + e.getLocalizedMessage());
            throw new GameFailedToStartException();
        }
    }
    
    public void startTurn() {
    	if (currentPlayer == players.get(0)) {
    		turnNum++;
    	}
    	currentPlayer.updateSimpleTiles(gBoard.getTiles());
    }

    public boolean isGameOver() {
        ArrayList<Capitol> capitols = this.currentPlayer.getCapitols();
        return capitols.size() == 0;
    }
    
    public void endTurn() {
        currentPlayer.endTurn();
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