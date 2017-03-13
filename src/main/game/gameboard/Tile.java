package game.gameboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.gameboard.areaEffect.AreaEffect;
import game.entities.BattleGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.EntityId;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.iTileActionVisitor;

// Tile class for gameboard
public class Tile implements iTileAccessors {
	private final static Logger log = LogManager.getLogger(Tile.class);
    private boolean containsUnit;
    private boolean containsArmy;
    private boolean containsRallyPoint;
    private boolean containsStructure;
    private boolean containsEffect;
    private Resource food;
    private Resource ore;
    private Resource peat;
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
    private ArrayList<RallyPoint> rallyPoints;
    private ArrayList<BattleGroup> battleGroups;
    private Structure structure;
    private AreaEffect effect;
    private Location location;
    private Random resourceValueGenerator = new Random();
    private int ownerId;

    Tile(TerrainEnum tileType, Location location) {
        Terrain = tileType;
        food = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        ore = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        peat = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        units = new ArrayList<Unit>();
        battleGroups = new ArrayList<BattleGroup>();
        rallyPoints = new ArrayList<RallyPoint>();
        containsStructure = false;
        containsRallyPoint = false;
        containsUnit = false;
        containsArmy = false;
        containsEffect = false;
        structure = null;
        effect = null;
        this.location = location;
        this.setOwnerId(-1);
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }
    public int getOwner() {
        if(this.units.isEmpty()){
            ownerId = -1;
        }
        else {
            ownerId = units.get(0).getOwnerID();
        }
        return this.ownerId;
    }

    public Location getLocation(){return location;}

    //TODO FIND BETTER WAY TO DO OWNER ID
    public void addUnit(Unit unit) {
        /*if(getOwner()!=-1 && unit.getOwnerID()!=ownerId){
            System.out.println("Good");
            return;
        }*/
        units.add(unit);
        if (containsUnit == false) { containsUnit = true; }
        //unit.setLocation(this.location);
    }
    public void addStructure(Structure structure){
        if(this.containsStructure == false){
            this.structure = structure;
            this.containsStructure = true;
        }
    }
    public void addBattleGroup(BattleGroup battleGroup){
        battleGroups.add(battleGroup);
    }
    public void addRallyPoint(RallyPoint rallyPoint){
        rallyPoints.add(rallyPoint);
        if (containsRallyPoint == false) { containsRallyPoint = true; }
    }
    public void addEffect(AreaEffect effect){
        if (this.containsEffect == false){
            this.effect = effect;
            this.containsEffect = true;
        }
    }

    public ArrayList<Unit> getUnits(){return units;}

    //test if terrain is impassable
    public boolean isImpassable(){
        return(Terrain == TerrainEnum.WATER || Terrain == TerrainEnum.NON_TILE || Terrain == TerrainEnum.MOUNTAIN);
    }

    public TerrainEnum getTileType() { return Terrain;}

    public void removeEntity(EntityId entityId) {
        //Units
        Iterator<Unit> unitIterator = units.iterator();
        while (unitIterator.hasNext()) {
            Unit uholder = unitIterator.next();
            if (entityId.compareTo(uholder.getEntityId())==1){
                unitIterator.remove();
                return;
            }
        }

        //Structure
        if (entityId.compareTo(structure.getEntityId())==1){
            this.structure = null;
            return;
        }

        //BattleGroup
        Iterator<BattleGroup> bgIterator = battleGroups.iterator();
        while (bgIterator.hasNext()) {
            BattleGroup bgholder = bgIterator.next();
            if (entityId.compareTo(bgholder.getEntityId())==1){
                bgIterator.remove();
                return;
            }
        }

        //Rallypoint
        Iterator<RallyPoint> rpIterator = rallyPoints.iterator();
        while (rpIterator.hasNext()) {
            RallyPoint rpholder = rpIterator.next();
            if (entityId.compareTo(rpholder.getEntityId())==1){
                rpIterator.remove();
                return;
            }
        }
    }

    public boolean containsUnit(){
        return containsUnit;
    }
    public boolean containsArmy() { return containsArmy; }
    public boolean containsRallyPoint() { return containsRallyPoint; }
    public boolean containsStructure() { return containsStructure; }
    public boolean containsEffect() { return containsEffect; }

    // Accept tile action visitors
    public void accept(iTileActionVisitor v) {
        // for (iEntity e : Entities) { e.accept(v) }
    }
    
    public ArrayList<BattleGroup> getBattleGroups(){return  battleGroups;}

    public ArrayList<RallyPoint> getRallyPoints() {
    	return rallyPoints;
    }

    public Structure getStructure() {
    	return structure;
    }

    public AreaEffect getEffect() { return effect; }

    public Resource getResource(ResourceTypeEnum resource) {
    	switch (resource) {
    	case FOOD:
    		return food;
    	case ORE:
    		return ore;
    	case PEAT:
    		return peat;
		default:
			log.error("Invalid Resource Type: " + resource + "not present on tile.");
			return null;
    	}
    }
}
