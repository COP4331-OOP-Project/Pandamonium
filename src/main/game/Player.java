package game;

import game.commands.MoveCommand;
import game.entities.*;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.*;
import game.entities.managers.*;
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
	private ArmyManager armyManager;
	private ResourceManager resourceManager;

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
		this.resourceManager = new ResourceManager(gb);
		this.workerManager = new WorkerManager(playerId,resourceManager);
		this.placementManager = new PlacementManager(gb);
		this.unitManager = new UnitManager(this, placementManager);
		this.structureManager = new StructureManager(this, placementManager, workerManager, unitManager);
		this.armyManager = new ArmyManager(playerId, gb);

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

	public UnitManager getUnitManager() { return this.unitManager; }

	public ArmyManager getArmyManager() { return this.armyManager; }

	public PlacementManager getPlacementManager() { return this.placementManager; }

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
		if (entityId != null) {
			return unitManager.getUnitById(entityId);
		} else {
			return null;
		}
	}
	
	public Structure getStructure(EntityId entityId) throws StructureDoesNotExistException{
		if (entityId != null) {
			return structureManager.getStructureById(entityId);
		} else {
			return null;
		}
	}
	
	public RallyPoint getRallyPoint(EntityId entityId) throws RallyPointDoesNotExistException{
		for (RallyPoint rally : rallyPoints) {
			if (entityId.compareTo(rally.getEntityId()) == 1) {
				return rally;
			}
		}
		throw new RallyPointDoesNotExistException();
	}
	
	public void removeEntity(EntityTypeEnum type, EntitySubtypeEnum subtype, EntityId entityId, Location location)
		throws EntityTypeDoesNotExistException, UnitDoesNotExistException, UnitTypeDoesNotExistException,
				StructureDoesNotExistException, StructureTypeDoesNotExist, WorkerDoesNotExistException,
				ArmyDoesNotExistException {

		switch (type) {
			case UNIT:
				this.unitManager.removeUnit(subtype, entityId);
				this.placementManager.remove(entityId, location);
				break;
			case STRUCTURE:
				this.structureManager.removeStructure(subtype,entityId);
				this.placementManager.remove(entityId, location);
				break;
			case WORKER:
				this.workerManager.removeWorker(entityId);
				this.placementManager.remove(entityId, location);
				break;
			case ARMY:
				this.armyManager.removeArmy(entityId);
				this.placementManager.remove(entityId, location);
				break;
			default:
				throw new EntityTypeDoesNotExistException("Entity type " + type + " does not exist.");
		}

	}

	public void attach(iTurnObserver observer) {
		this.turnObservers.add(observer);
	}


	boolean test = false;

	public void endTurn() {
		if (!test) {
			Colonist c = this.unitManager.getColonists().get(0);
			Location moveLocation = new Location(c.getLocationX() + 2, c.getLocationY() - 2);
			Location moveLocation2 = new Location(moveLocation.getX(), moveLocation.getY() - 2);
			Location moveLocation3 = new Location(moveLocation2.getX(), moveLocation2.getY() - 2);
			Location moveLocation4 = new Location(5, 29);

			MoveCommand mc = new MoveCommand(c, moveLocation, 1, 1);
			MoveCommand mc2 = new MoveCommand(c, moveLocation2, 1, 1);
			MoveCommand mc3 = new MoveCommand(c, moveLocation3, 1, 1);
			MoveCommand mc4 = new MoveCommand(c, moveLocation4, 1, 1);

			c.addCommandToQueue(mc);
			c.addCommandToQueue(mc2);
			c.addCommandToQueue(mc3);
			c.addCommandToQueue(mc4);
			test = true;

		}

		for (iTurnObserver observer : this.turnObservers) {
			observer.onTurnEnded();
		}
		unitManager.upkeepHandling(nutrients);
	}


	public void addNutrients(Resource nutrients) {
		this.nutrients.combine(nutrients);
	}

	public void addPower(Resource power) {
		this.power.combine(power);
	}

	public void addMetal(Resource metal) {
		this.metal.combine(metal);
	}
}