package game;

import java.util.ArrayList;

import javax.lang.model.UnknownEntityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.structures.Capitol;
import game.entities.structures.Farm;
import game.entities.structures.Fort;
import game.entities.structures.Mine;
import game.entities.structures.ObservationTower;
import game.entities.structures.PowerPlant;
import game.entities.Army;
import game.entities.EntitySubtypeEnum;
import game.entities.RallyPoint;
import game.entities.factories.UnitFactory;
import game.entities.factories.exceptions.ColonistLimitExceededException;
import game.entities.factories.exceptions.ExplorerLimitExceededException;
import game.entities.factories.exceptions.MeleeLimitExceededException;
import game.entities.factories.exceptions.RangedLimitExceededException;
import game.entities.structures.Structure;
import game.entities.structures.University;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Unit;
import game.entities.units.exceptions.UnitNotFoundException;
import game.entities.workers.workerTypes.Worker;
import game.gameboard.Location;
import game.gameboard.SimpleTile;
import game.gameboard.SimpleTileUpdater;
import game.gameboard.Tile;


public class Player {
    private final static Logger log = LogManager.getLogger(Player.class);

    //Player ID of this player
    private int playerId;

    //ArrayLists of this player's instances
    private ArrayList<Army> armies;
    private ArrayList<Melee> melees;
    private ArrayList<Ranged> ranges;
    private ArrayList<Explorer> explorers;
    private ArrayList<Colonist> colonists;
    private ArrayList<Worker> workers;
    private ArrayList<Structure> structures;
    private ArrayList<RallyPoint> rallyPoints;
    private ArrayList<Unit> totalUnits;
    private int totalUnitCount;
    private SimpleTile[][] simpleTiles;
    private UnitFactory unitFactory = new UnitFactory();
    //Initial Units
    private Colonist initialColonist;

    public Player(int playerId, Location loc) {
        //loc is referring to the starting location of this player
        this.playerId = playerId;
		try {
			initialColonist = (Colonist)unitFactory.createUnit(EntitySubtypeEnum.COLONIST, loc, playerId);
		} catch (ColonistLimitExceededException | ExplorerLimitExceededException | RangedLimitExceededException
				| MeleeLimitExceededException | UnitNotFoundException e) {
			e.printStackTrace();
		}
        init();
    }

    private void init(){
        //Initialize all the arraylists of thsi player
        armies = new ArrayList<Army>();
        melees = new ArrayList<Melee>();
        ranges = new ArrayList<Ranged>();
        explorers = new ArrayList<Explorer>();
        colonists = new ArrayList<Colonist>();
        workers = new ArrayList<Worker>();
        structures = new ArrayList<Structure>();
        rallyPoints = new ArrayList<RallyPoint>();
        totalUnits = new ArrayList<Unit>();
    }

    public void addMelee(Melee melee){
        melees.add(melee);
    }

    public void addRanged(Ranged ranged){
        ranges.add(ranged);
    }

    public void addExplorer(Explorer explorer){
        explorers.add(explorer);
    }

    public void addColonist(Colonist colonist){
        colonists.add(colonist);
    }

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public void removeMelee(Melee melee){
    	melees.remove(melee);
    }

    public void removeRanged(Ranged ranged){
    	ranges.remove(ranged);
    }

    public void removeExplorer(Explorer explorer){
    	explorers.remove(explorer);
    }

    public void removeColonist(Colonist colonist){
    	colonists.remove(colonist);
    }
    
    public void removeWorker(Worker worker){
        workers.remove(worker);
    }
    
    
    public void addArmy(Army army){
        armies.add(army);
    }

    public void addRallyPoint(RallyPoint rallyPoint){
        rallyPoints.add(rallyPoint);
    }
   
    public void removeArmy(Army army){
        armies.set(army.getInstanceId(), null);
    }

    public ArrayList<Melee> getMelees() {
        return melees;
    }

    public ArrayList<Ranged> getRanges() {
        return ranges;
    }

    public ArrayList<Explorer> getExplorers() {
        return explorers;
    }

    public ArrayList<Colonist> getColonists() {
        return colonists;
    }

    public ArrayList<Unit> getAllUnit() {
        return totalUnits;
    }
    
    public ArrayList<Worker> getWorkers(){
        return workers;
    }

    public void addCapitol(Capitol capitol){
        structures.add(capitol);
    }

    public void addFarm(Farm farm){
        structures.add(farm);
    }

    public void addFort(Fort fort){
        structures.add(fort);
    }

    public void addMine(Mine mine){
        structures.add(mine);
    }

    public void addObservationTower(ObservationTower tower){
        structures.add(tower);
    }

    public void addPowerPlant(PowerPlant powerPlant){
        structures.add(powerPlant);
    }

    public void addUniversity(University university){
        structures.add(university);
    }

    public void removeCapitol(Capitol capitol){
        structures.remove(capitol);
    }
    
    public void removeFarm(Farm farm){
        structures.remove(farm);
    }

    public void removeFort(Fort fort){
        structures.remove(fort);
    }

    public void removeMine(Mine mine){
        structures.remove(mine);
    }

    public void removeObservationTower(ObservationTower tower){
        structures.remove(tower);
    }

    public void removePowerPlant(PowerPlant powerPlant){
        structures.remove(powerPlant);
    }

    public void removeUniversity(University university){
        structures.remove(university);
    }
    
    public void initializeSimpleTiles(Tile[][] tiles) {
    	simpleTiles = new SimpleTile[tiles.length][tiles[0].length];
    	for (int i = 0; i < simpleTiles.length; i++) {
    		for (int j = 0; j < simpleTiles[i].length; j++) {
    			simpleTiles[i][j] = new SimpleTile(tiles[i][j]);
    		}
    	}
    }
    
    public ArrayList<Structure> getStructures() {
    	return structures;
    }
    
    public void updateSimpleTiles(Tile[][] tiles) {
    	simpleTiles = SimpleTileUpdater.updateTiles(tiles, simpleTiles, this);
    }
    
    public SimpleTile[][] getSimpleTiles() {
    	return simpleTiles;
    }
}