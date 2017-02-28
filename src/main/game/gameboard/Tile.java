package game.gameboard;

//import game.entities.Army;
//import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.visitors.iTileActionVisitor;
import game.entities.EntityId;

import java.util.ArrayList;
// Tile class for gameboard
public class Tile implements iTileAccessors {
    private boolean containsUnit;
    private boolean containsArmy;
    private boolean containsRallyPoint;
    private boolean containsStructure;

    //public Resource tileResource;
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
//    private ArrayList<Army> armies;
//    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Location location;
    private int ownerId;

    Tile(TerrainEnum tileType, Location location) {
        this.Terrain = tileType;
        this.units = new ArrayList<Unit>();
        //this.armies = new ArrayList<Army>();
        //this.rallyPoints = new ArrayList<RallyPoint>();
        this.containsStructure = false;
        this.containsRallyPoint = false;
        this.containsUnit = false;
        this.containsArmy = false;
        this.structure = null;
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
        return(Terrain == TerrainEnum.WATER || Terrain == TerrainEnum.INVISIBLE);
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

    public boolean containsUnit(){
        return false;
    }

    public boolean containsArmy(){
        return false;
    }

    // Accept tile action visitors
    public void accept(iTileActionVisitor v) {
        // for (iEntity e : Entities) { e.accept(v) }
    }
}
