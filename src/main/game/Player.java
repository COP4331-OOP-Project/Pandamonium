package game;

import game.commands.*;
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
import game.entityTypeResearch.UniversityAlreadyDoingResearchException;
import game.gameboard.*;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.techTree.TechTree;
import game.techTree.nodeTypes.TechTreeNode;

import game.techTree.nodeTypes.ProductionRateIntegerResearchNode;
import game.techTree.nodeTypes.TechNodeImageEnum;
import game.techTree.nodeTypes.TechTreeNode;
import game.visitors.AttackVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Player implements iTurnObservable {

	// Logger
	private final static Logger log = LogManager.getLogger(Player.class);

	// Player ID of this player
	private int playerId;

	// ArrayLists of this player's instances
	private ArrayList<RallyPoint> rallyPoints;
	private Gameboard gameboard;

	// Managers
	private WorkerManager workerManager;
	private UnitManager unitManager;
	private StructureManager structureManager;
	private PlacementManager placementManager;
	private ArmyManager armyManager;
	private ResourceManager resourceManager;
	private TechTree techTree;
	
	// Player visibility board
	private SimpleTile[][] simpleTiles;

	// Player resource counts
	private Resource nutrients = new Resource(100, ResourceTypeEnum.NUTRIENTS);
	private Resource power = new Resource(500, ResourceTypeEnum.POWER);
	private Resource metal = new Resource(500, ResourceTypeEnum.METAL);

	private ArrayList<iTurnObserver> turnObservers;
	private int turnCounter = 1;	// for demo purposes

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

		this.rallyPoints = new ArrayList<RallyPoint>();

		this.gameboard = gb;

		this.turnObservers = new ArrayList<>();
		this.attach(this.workerManager);
		this.attach(this.unitManager);
		this.attach(this.structureManager);
		
		techTree = new TechTree(this, placementManager, structureManager, workerManager);
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

	public Army addArmy(EntityTypeEnum type, ArrayList<Unit> units, Location loc) throws EntityTypeDoesNotExistException {
		try {
			switch (type) {
				case ARMY:
					return this.armyManager.addArmy(units, loc);
				default:
					throw new EntityTypeDoesNotExistException("The input type " + type + " is not army.");
			}
		} catch(ArmyDoesNotExistException|ArmyLimitExceededException e){
			log.error("Army can't be created");
		}
		return null;
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
		return armyManager.getArmyById(entityId);
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

	public ArrayList<Army> getArmies(){return armyManager.getArmies();}
	
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



	public void endTurn() {
		doDemoMove();
		unitManager.upkeepHandling(nutrients);

		for (iTurnObserver observer : this.turnObservers) {
			observer.onTurnEnded();
		}
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

	
	public ArrayList<TechTreeNode> getRootTechs() {
		return techTree.getRootNodes();
	}




	////////////				DEMO STUFF			/////////////////

	private void doDemoMove() {
		switch (this.turnCounter) {
			case 1:
				one();
				break;
			case 3:
				two();
				break;
			case 5:
				three();
				break;
			case 7:
				four();
				break;
			case 9:
				five();
				break;
			case 11:
				six();
				break;
			case 13:
				break;
			case 15:
				seven();
				break;
			case 17:
				eight();
				ten();
				break;
			case 19:
				nine();
				break;
			default:
				break;


		}
		this.turnCounter++;
	}

	Colonist c1;
	private void one() {
		this.c1 = this.unitManager.getColonists().get(0);
		Command powerDown = new PowerDownCommand(this.c1);
		this.c1.addCommandToQueue(powerDown);
	}

	private Melee m;
	private Melee m2;
	private Explorer e;
	private void two() {
		Command powerUp = new PowerUpCommand(this.c1);
		this.c1.addCommandToQueue(powerUp);
	}

	private void three() {
		FoundCapitolCommand command = new FoundCapitolCommand(this.c1, this.c1.getLocation(), 2, this.structureManager, this.unitManager, this.workerManager);
		this.c1.addCommandToQueue(command);
	}

	private void four() {
		try {
			if (this.getPlayerId() == 0) {
				this.m = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 30));
				this.m2 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 30));
				Command command = new MakeStructureCommand(m, new Location(5, 30), 1, EntitySubtypeEnum.FARM, this.structureManager);
				m.addCommandToQueue(command);

				Command makeUniversity = new MakeStructureCommand(m, new Location(4, 30), 1, EntitySubtypeEnum.UNIVERSITY, this.structureManager);
				m.addCommandToQueue(makeUniversity);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	Melee p1;
	private void five() {
		if (this.m != null) {
			MoveCommand moveCommand = new MoveCommand(this.m, new Location(5,31), 0, 1);
			this.m.addCommandToQueue(moveCommand);

			University u = this.getUniversities().get(0);
			TechTreeNode node = new ProductionRateIntegerResearchNode(this.workerManager, "", "", TechNodeImageEnum.FOOD, WorkerTypeEnum.FOOD_GATHERER, 50);
			try {
				u.research(node);
			} catch (UniversityAlreadyDoingResearchException e) {
				log.error("oh well");
			}
		}

		if (this.getPlayerId() == 1) {
			this.p1 = this.getMelees().get(0);
			Command command = new MakeStructureCommand(this.p1, new Location(7, 28), 1, EntitySubtypeEnum.MINE, this.structureManager);
			p1.addCommandToQueue(command);
		}
	}

	private void six() {
		if (this.m2 != null) {
			MoveCommand moveCommand = new MoveCommand(this.m2, new Location(6, 29), 0, 1);
			this.m2.addCommandToQueue(moveCommand);

			MoveCommand moveCommand2 = new MoveCommand(this.m2, new Location(5, 30), 0, 1);
			this.m2.addCommandToQueue(moveCommand2);
		}
	}

	private Melee m3;
	private Melee m4;
	private Melee m5;

	private Melee m6;
	private Melee m7;
	private Melee m8;

	private void seven() {
		if (this.getPlayerId() == 1) {
			MoveCommand moveCommand = new MoveCommand(this.p1, new Location(8,28), 0, 1);
			this.p1.addCommandToQueue(moveCommand);
		}

		if (this.playerId == 0) {

			try {

				this.m3 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 25));
				this.m4 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 25));
				this.m5 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 25));

			} catch (Exception e) {
				e.getLocalizedMessage();
			}


			ArrayList<Unit> units = new ArrayList<>();
			units.add(m3);
			units.add(m4);
			units.add(m5);

			try {
				this.armyManager.addArmy(units, new Location(6, 25));
			} catch (ArmyLimitExceededException e) {
				e.getLocalizedMessage();
			} catch (ArmyDoesNotExistException e) {
				e.getLocalizedMessage();
			}

		} else {

			try {

				this.m6 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 26));
				this.m7 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 26));
				this.m8 = (Melee) this.unitManager.addUnit(EntitySubtypeEnum.MELEE, new Location(5, 26));

			} catch (Exception e) {
				e.getLocalizedMessage();
			}


			ArrayList<Unit> units = new ArrayList<>();
			units.add(m6);
			units.add(m7);
			units.add(m8);

			try {
				this.armyManager.addArmy(units, new Location(6, 26));
			} catch (ArmyLimitExceededException e) {
				e.getLocalizedMessage();
			} catch (ArmyDoesNotExistException e) {
				e.getLocalizedMessage();
			}

		}
	}

	public void eight() {
		if (this.playerId == 0) {

			Tile t = gameboard.getTileWithLocation(new Location(5, 26));

			AttackVisitor atk = new AttackVisitor(m3.getDamage());
			t.accept(atk);

		} else {

			Tile t = gameboard.getTileWithLocation(new Location(5, 25));

			AttackVisitor atk = new AttackVisitor(m6.getDamage());
			t.accept(atk);

		}
	}

	public void nine() {

		if (this.playerId == 0) {

			Tile t = gameboard.getTileWithLocation(new Location(5, 26));

			AttackVisitor atk = new AttackVisitor(m3.getDamage() * 20);
			t.accept(atk);

		}
	}

	private void ten(){
		try {
			if (this.getPlayerId() == 0) {
				this.e = (Explorer) this.unitManager.addUnit(EntitySubtypeEnum.EXPLORER, new Location(6, 30));
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		if(this.getPlayerId() == 0) {
			StartProspectingCommand startProspectingCommand = new StartProspectingCommand(this.e, 1);
			this.e.addCommandToQueue(startProspectingCommand);
		}
	}
}