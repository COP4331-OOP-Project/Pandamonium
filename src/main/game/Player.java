package game;

import game.entities.*;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.*;
import game.entities.managers.PlacementManager;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.*;
import game.entities.structures.*;
import game.entities.units.*;
import game.entities.units.exceptions.UnitNotFoundException;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.*;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class Player implements iTurnObservable {

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
	private PlacementManager placementManager;

	// Player visibility board
	private SimpleTile[][] simpleTiles;

	// Player resource counts
	private Resource nutrients = new Resource(0, ResourceTypeEnum.NUTRIENTS);
	private Resource power = new Resource(0, ResourceTypeEnum.POWER);
	private Resource metal = new Resource(0, ResourceTypeEnum.METAL);

	private ArrayList<iTurnObserver> turnObservers;

	// Constructor
	public Player(int playerId, Location loc, Gameboard gb) {
		this.playerId = playerId;	// Set player id

		// Setup managers for entities, workers
		this.workerManager = new WorkerManager(playerId);
		this.placementManager = new PlacementManager(gb);
		this.unitManager = new UnitManager(this, placementManager);
		this.structureManager = new StructureManager(this, placementManager, workerManager);

		this.armies = new ArrayList<Army>();
		this.rallyPoints = new ArrayList<RallyPoint>();

		this.turnObservers = new ArrayList<>();
		this.attach(this.workerManager);
		this.attach(this.unitManager);
		this.attach(this.structureManager);

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

	// Get entity by id
	public Entity getEntityById(EntityId id) throws UnitNotFoundException {
		try {
			return unitManager.getUnitById(id);
		} catch (UnitDoesNotExistException ex) {}
		try {
			return this.structureManager.getStructureById(id);
		} catch (StructureDoesNotExistException ex) {}

		throw new UnitNotFoundException();
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

	public WorkerManager getWorkerManager(){return workerManager;}

	public StructureManager getStructureManager(){return structureManager;}

	public void updateSimpleTiles(Tile[][] tiles) {
		simpleTiles = SimpleTileUpdater.updateTiles(tiles, simpleTiles, this);
	}

	public SimpleTile[][] getSimpleTiles() {
		return simpleTiles;
	}

	public int getPlayerId() { return this.playerId; }

	public Army getArmy(EntityId entityId) throws ArmyDoesNotExistException {
		for (Army army : armies) {
			if (entityId.compareTo(army.getEntityId()) == 1) {
				return army;
			}
		}
		throw new ArmyDoesNotExistException();
	}
	
	public Unit getUnit(EntityId entityId) throws UnitDoesNotExistException{
		return unitManager.getUnitById(entityId);
	}
	
	public Structure getStructure(EntityId entityId) throws StructureDoesNotExistException{
		return structureManager.getStructureById(entityId);
	}
	
	public RallyPoint getRallyPoint(EntityId entityId) throws RallyPointDoesNotExistException{
		for (RallyPoint rally : rallyPoints) {
			if (entityId.compareTo(rally.getEntityId()) == 1) {
				return rally;
			}
		}
		throw new RallyPointDoesNotExistException();
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

	public void attach(iTurnObserver observer) {
		this.turnObservers.add(observer);
	}

	public void endTurn() {
		for (iTurnObserver observer : this.turnObservers) {
			observer.onTurnEnded();
		}
	}
}