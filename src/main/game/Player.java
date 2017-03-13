package game;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.lang.model.UnknownEntityException;

import game.entities.*;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.*;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.*;
import game.entities.workers.workerTypes.Worker;

import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.factories.UnitFactory;
import game.entities.structures.Capitol;
import game.entities.structures.Farm;
import game.entities.structures.Fort;
import game.entities.structures.Mine;
import game.entities.structures.ObservationTower;
import game.entities.structures.PowerPlant;
import game.entities.structures.Structure;
import game.entities.structures.University;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Unit;
import game.entities.units.exceptions.UnitNotFoundException;
import game.entities.workers.workerTypes.Worker;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

public class Player {

	// Logger
	private final static Logger log = LogManager.getLogger(Player.class);

	// Player ID of this player
	private int playerId;

	// ArrayLists of this player's instances
	private ArrayList<Army> armies;
	private ArrayList<RallyPoint> rallyPoints;

	// Managers
	private WorkerManager workerManager;
	private UnitManager unitManager;
	private StructureManager structureManager;

	// Player visibility board
	private SimpleTile[][] simpleTiles;

	// Player resource counts
	private Resource nutrients = new Resource(0, ResourceTypeEnum.NUTRIENTS);
	private Resource power = new Resource(0, ResourceTypeEnum.POWER);
	private Resource metal = new Resource(0, ResourceTypeEnum.METAL);

	// Constructor
	public Player(int playerId, Location loc, Gameboard gb) {
		this.playerId = playerId;	// Set player id

		// Setup managers for entities, workers
		this.workerManager = new WorkerManager(playerId);
		this.unitManager = new UnitManager(playerId, gb);
		this.structureManager = new StructureManager(playerId, gb);

		this.armies = new ArrayList<Army>();
		this.rallyPoints = new ArrayList<RallyPoint>();
	}

	// Add entity of designated type, subtype @ given location
	public Entity addEntity(EntityTypeEnum type, EntitySubtypeEnum subtype, Location location)
		throws EntityTypeDoesNotExistException, UnitTypeDoesNotExistException, UnitTypeLimitExceededException,
				StructureTypeDoesNotExist, StructureTypeLimitExceededException, TotalUnitLimitExceededException,
				TotalStructureLimitExceededException {

		switch (type) {
			case UNIT:
				return this.unitManager.addUnit(subtype, location);
			case STRUCTURE:
				return this.structureManager.addStructure(subtype, location);
			default:
				throw new EntityTypeDoesNotExistException("Entity of type " + type + " does not exist.");
		}

	}

	// Add worker of designated subtype @ given location
	public Worker addEntity(EntityTypeEnum type, WorkerTypeEnum subtype, Location location)
			throws EntityTypeDoesNotExistException, WorkerLimitExceededException, WorkerTypeDoesNotExist {

		if (type == EntityTypeEnum.WORKER) {
			return this.workerManager.addWorker(subtype, location);
		} else throw new EntityTypeDoesNotExistException("Entity is not of type Worker.");

	}

	public void addArmy(Army army) {
		armies.add(army);
	}

	public void addRallyPoint(RallyPoint rallyPoint) {
		rallyPoints.add(rallyPoint);
	}

	// Get all colonists
	public ArrayList<Colonist> getColonists() {
		return this.unitManager.getColonists();
	}

	// Get all explorers
	public ArrayList<Explorer> getExplorers() {
		return this.unitManager.getExplorers();
	}

	// Get all melees
	public ArrayList<Melee> getMelees() {
		return this.unitManager.getMelees();
	}

	// Get all ranges
	public ArrayList<Ranged> getRanges() {
		return this.unitManager.getRanges();
	}

	// Get all units
	public ArrayList<Unit> getUnits() {
		return this.unitManager.getTotalUnits();
	}

	// Get all capitols
	public ArrayList<Capitol> getCapitols() {
		return this.structureManager.getCapitols();
	}

	// Get all farms
	public ArrayList<Farm> getFarms() {
		return this.structureManager.getFarms();
	}

	// Get all forts
	public ArrayList<Fort> getForts() {
		return this.structureManager.getForts();
	}

	// Get all mines
	public ArrayList<Mine> getMines() {
		return this.structureManager.getMines();
	}

	// Get all observationTowers
	public ArrayList<ObservationTower> getObservationTowers() {
		return this.structureManager.getObservationTowers();
	}

	// Get all powerPlants
	public ArrayList<PowerPlant> getPowerPlant() {
		return this.structureManager.getPowerPlants();
	}

	// Get all universities
	public ArrayList<University> getUniversities() {
		return this.structureManager.getUniversities();
	}

	// Get all structures
	public ArrayList<Structure> getStructures() {
		return this.structureManager.getTotalStructures();
	}

	// Get all workers
	public ArrayList<Worker> getWorkers() {
		return this.workerManager.getWorkers();
	}

	// Create simple tiles
	public void initializeSimpleTiles(Tile[][] tiles) {
		simpleTiles = new SimpleTile[tiles.length][tiles[0].length];
		for (int i = 0; i < simpleTiles.length; i++) {
			for (int j = 0; j < simpleTiles[i].length; j++) {
				simpleTiles[i][j] = new SimpleTile(tiles[i][j]);
			}
		}
	}

	public ArrayList<RallyPoint> getRallyPoints() {
		return rallyPoints;
	}

	public ArrayList<Army> getArmies() {
		return armies;
	}

	public Resource getNutrients() {
		return nutrients;
	}

	public Resource getPower() {
		return power;
	}

	public Resource getMetal() {
		return metal;
	}

	public void updateSimpleTiles(Tile[][] tiles) {
		simpleTiles = SimpleTileUpdater.updateTiles(tiles, simpleTiles, this);
	}

	public SimpleTile[][] getSimpleTiles() {
		return simpleTiles;
	}

	public void removeEntity(EntityTypeEnum type, EntitySubtypeEnum subtype, EntityId entityId)
		throws EntityTypeDoesNotExistException, UnitDoesNotExistException, UnitTypeDoesNotExistException,
				StructureDoesNotExistException, StructureTypeDoesNotExist, WorkerDoesNotExistException {

		switch (type) {
			case UNIT:
				this.unitManager.removeUnit(subtype, entityId);
				break;
			case STRUCTURE:
				this.structureManager.removeStructure(subtype,entityId);
				break;
			case WORKER:
				this.workerManager.removeWorker(entityId);
				break;
//			case ARMY:
//				this.armyManager.removeArmy(entityId);
			default:
		}

		for(int i = 0; i < armies.size(); i++) {
			if(entityId.compareTo(armies.get(i).getEntityId())==1){
				armies.remove(i);
				return;
			}
		}

		throw new EntityTypeDoesNotExistException("Entity type " + type + " does not exist.");

	}
}