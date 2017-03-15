package game.gameboard;

import game.entities.BattleGroup;
import game.entities.EntityId;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.areaEffects.AreaEffect;
import game.gameboard.areaEffects.AreaEffectAlreadyPresentException;
import game.gameboard.oneShotItem.OneShotItem;
import game.gameboard.oneShotItem.OneShotItemAlreadyPresentException;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.iTileActionVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// Tile class for gameboard
public class Tile implements iTileAccessors, iTileObservable {
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
    private ArrayList<RallyPoint> rallyPoints;
    private ArrayList<BattleGroup> battleGroups;
    private ArrayList<iTileObserver> influencedBy;
    private Structure structure;
    private Location location;
    private Random resourceValueGenerator = new Random();
    private Integer ownerId;
    private AreaEffect areaEffect;
    private OneShotItem oneShotItem;


    Tile(TerrainEnum tileType, Location location) {
        Terrain = tileType;
        food = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        ore = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        peat = new Resource(resourceValueGenerator.nextDouble() * 300, ResourceTypeEnum.FOOD);
        units = new ArrayList<>();
        battleGroups = new ArrayList<>();
        rallyPoints = new ArrayList<>();
        influencedBy = new ArrayList<>();
        containsStructure = false;
        containsRallyPoint = false;
        containsUnit = false;
        containsArmy = false;
        structure = null;
        this.location = location;
        this.setOwnerId(null);
    }

    Tile(TerrainEnum tileType, Location location, AreaEffect areaEffect) {
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
        structure = null;
        this.location = location;
        this.areaEffect = areaEffect;
        this.setOwnerId(null);
    }

    Tile(TerrainEnum tileType, Location location, OneShotItem oneShotItem) {
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
        structure = null;
        this.location = location;
        this.oneShotItem = oneShotItem;
        this.setOwnerId(null);
    }

    public void setOwnerId(Integer ownerId){
        this.ownerId = ownerId;
    }

    public Integer getOwner() {
        if(this.units.isEmpty()){
            ownerId = null;
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
        if (this.areaEffect != null)
            this.areaEffect.affectUnit(unit);

        if (this.oneShotItem != null) {
            if (!this.oneShotItem.isUsed())
                this.oneShotItem.useItem(unit);
            else
                this.oneShotItem = null;
        }
        //unit.setLocation(this.location);
    }

    public void addAreaEffect(AreaEffect areaEffect) throws AreaEffectAlreadyPresentException, OneShotItemAlreadyPresentException {
        if (this.areaEffect != null) throw new AreaEffectAlreadyPresentException();
        if (this.oneShotItem != null) throw new OneShotItemAlreadyPresentException();

        this.areaEffect = areaEffect;
    }

    public void addOneShotItem(OneShotItem oneShotItem) throws AreaEffectAlreadyPresentException, OneShotItemAlreadyPresentException {
        if (this.areaEffect != null) throw new AreaEffectAlreadyPresentException();
        if (this.oneShotItem != null) throw new OneShotItemAlreadyPresentException();

        this.oneShotItem = oneShotItem;
    }

    public void addStructure(Structure structure){
        if(!containsStructure())
            this.structure = structure;

    }

    public void addBattleGroup(BattleGroup battleGroup){
        battleGroups.add(battleGroup);
    }

    public void addRallyPoint(RallyPoint rallyPoint){
        rallyPoints.add(rallyPoint);
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

    public boolean containsStructure(){
        return (structure!=null);
    }

    public boolean containsUnit(){
        return (!units.isEmpty());
    }

    public boolean containsArmy(){
        //check if the tile contains army
        return false;
    }

    // Accept tile action visitors
    public void accept(iTileActionVisitor v) {
        // for (iEntity e : Entities) { e.accept(v) }
    }

    // Observable
    public void attach(iTileObserver o){ influencedBy.add(o); }

    public ArrayList<BattleGroup> getBattleGroups(){return  battleGroups;}

    public ArrayList<RallyPoint> getRallyPoints() {
        return rallyPoints;
    }

    public ArrayList<iTileObserver> getInfluencedBy(){ return influencedBy; }

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

    public AreaEffect getAreaEffect() {
        return this.areaEffect;
    }

    public Integer getOwnerId() { return ownerId; }

    public OneShotItem getOneShotItem() { return this.oneShotItem; }

}
