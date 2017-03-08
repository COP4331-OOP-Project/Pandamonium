package game.gameboard;

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.Army;
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
    public boolean containsUnit;
    public boolean containsArmy;
    public boolean containsRallyPoint;
    public boolean containsStructure;
    public Resource food;
    public Resource ore;
    public Resource peat;
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Location location;
    private Random resourceValueGenerator = new Random();
    private int ownerId;

    Tile(TerrainEnum tileType, Location location) {
        Terrain = tileType;
        food = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        ore = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        peat = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        units = new ArrayList<Unit>();
        //armies = new ArrayList<Army>();
        //rallyPoints = new ArrayList<RallyPoint>();
        containsStructure = false;
        containsRallyPoint = false;
        containsUnit = false;
        containsArmy = false;
        structure = null;
        this.location = location;
        this.setOwnerId(-1);
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    public int getOwner() {
        return this.ownerId;
    }

    public Location getLocation(){return location;}

    public void addUnit(Unit unit){
        units.add(unit);
        //unit.setLocation(this.location);
    }

    public void addStructure(Structure structure){
        if(containsStructure()==false){
            this.structure = structure;
        }
    }

//    public void addArmy(Army army) {
//        armies.add(army);
//    }

    //test if terrain is impassable
    public boolean isImpassable(){
        return(Terrain == TerrainEnum.WATER || Terrain == TerrainEnum.NON_TILE || Terrain == TerrainEnum.MOUNTAIN);
    }

    public TerrainEnum getTileType() { return Terrain;}

    public void removeEntity(EntityId entityId) {
        for (int i = 0; i < units.size(); i++) {
            if (entityId.compareTo(units.get(i).getEntityId())==1)
                units.remove(i);
        }

        if (entityId.compareTo(structure.getEntityId())==1){
            this.structure = null;
        }

//        for (int i = 0; i<armies.size();i++){
//            if(entityId.compareTo(armies.get(i).getEntityId()))
//                armies.remove(i);
//        }
    }

    public boolean containsStructure(){
        return (structure!=null);
    }

    public boolean getContainUnit(){
        return (!units.isEmpty());
    }

    public boolean getContainArmy(){
        //check if the tile contains army
        return false;
    }

    // Accept tile action visitors
    public void accept(iTileActionVisitor v) {
        // for (iEntity e : Entities) { e.accept(v) }
    }
    
    public ArrayList<Unit> getUnits() {
    	return units;
    }
    
    public ArrayList<Army> getArmies() {
    	return armies;
    }
    
    public ArrayList<RallyPoint> getRallyPoints() {
    	return rallyPoints;
    }
    
    public Structure getStructure() {
    	return structure;
    }
    
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
