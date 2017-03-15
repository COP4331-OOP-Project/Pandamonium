package game.gameboard;

//import game.entities.Army;
//import game.entities.structures.Structure;
//import game.entities.units.Unit;
import game.visitors.iTileActionVisitor;

/**
 * Necessary commands for Tile access and management
 */
public interface iTileAccessors {

//    // Tile attribute accessors
//    Location getLocation();
//
//    TerrainEnum getTileType();
//
//    int getOwnerID();
//
//    // Tile attribute setters
//    void setOwnerID(int playerID);
//
//    // Boolean testers
//    boolean hasEnemyUnit(int playerID);
//
//    boolean isImpassable();
//
//    // Get entity commands
//    Unit getUnitById(int id) throws ObserverNotFoundException;
//
//    Army getArmyById(int id) throws ObserverNotFoundException;
//
//    ArrayList<Unit> getUnits();
//
//    ArrayList<Army> getArmies();
//
//    Structure getStructure();
//
//    void setStructure(Structure structure);
//
//    // Remove entity commands
//    void removeArmy(int armyID);
//
//    void removeStructure();
//
//    void removeUnit(int unitID);
//
//    // Add entity commands
//    void addUnit(Unit unit);
//
//    void addArmy(Army army);

    void accept(iTileActionVisitor v);

}
