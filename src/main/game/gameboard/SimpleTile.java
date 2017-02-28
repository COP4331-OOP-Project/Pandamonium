package game.gameboard;

import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.entities.Army;
import game.entities.RallyPoint;

import java.util.ArrayList;
public class SimpleTile{
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    TileVisibilityEnum visibility = TileVisibilityEnum.INVISIBLE;

    SimpleTile(TerrainEnum tileType) {
        Terrain = tileType;
        units = new ArrayList<Unit>();
        armies = new ArrayList<Army>();
        rallyPoints = new ArrayList<RallyPoint>();
        structure = null;
    }

    public void updateTile(Tile tile) {
    	if (visibility == TileVisibilityEnum.VISIBLE) {
    		
    	}
    }
    
    public ArrayList<Army> getArmies() {
    	return armies;
    }
    
    public ArrayList<RallyPoint> getRallyPoints() {
    	return rallyPoints;
    }
    
    public ArrayList<Unit> getUnits(){
    	return units;
    }
    public TerrainEnum getTileType() {
    	return Terrain;
    }
    
    public Structure getStructure() {
    	return structure;
    }
    
    public TileVisibilityEnum getVisibility() {
    	return visibility;
    }
    
    public void setVisibility(TileVisibilityEnum visibility) {
    	this.visibility = visibility;
    }
}