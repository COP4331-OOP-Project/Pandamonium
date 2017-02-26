package game.gameboard;

//import game.entities.Army;
//import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.visitors.iTileActionVisitor;
import game.entities.EntityId;

// Tile class for gameboard
public class Tile implements iTileAccessors {
    public boolean containsUnit;
    public boolean containsArmy;
    public boolean containsRallyPoint;
    public boolean containsStructure;
    //public Resource tileResource;
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
//    private ArrayList<Army> armies;
//    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Location location;
    private int ownerId;

    Tile(TerrainEnum tileType, Location location) {
        Terrain = tileType;
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
        if(getContainStructure()==false){
            this.structure = structure;
        }
    }

//    public void addArmy(Army army) {
//        armies.add(army);
//    }

    //test if terrain is impassable
    public boolean isImpassable(){
        return (Terrain == TerrainEnum.INVISIBLE || Terrain == TerrainEnum.WATER) ? true : false;
    }

    public TerrainEnum getTileType() { return Terrain;}

    public void removeEntity(EntityId entityId) {
        for (int i = 0; i < units.size(); i++) {
            if (entityId.compareTo(units.get(i).getEntityId()))
                units.remove(i);
        }

        if (entityId.compareTo(structure.getEntityId())){
            this.structure = null;
        }

//        for (int i = 0; i<armies.size();i++){
//            if(entityId.compareTo(armies.get(i).getEntityId()))
//                armies.remove(i);
//        }
    }

    public boolean getContainStructure(){
        if(structure!=null)
            return true;
        else
            return false;
    }

    public boolean getContainUnit(){
        if(units.isEmpty()== true)
            return false;
        else
            return true;
    }

    public boolean getContainArmy(){
        //check if the tile contains army
        return false;
    }

    // Accept tile action visitors
    public void accept(iTileActionVisitor v) {
        // for (iEntity e : Entities) { e.accept(v) }
    }
}
