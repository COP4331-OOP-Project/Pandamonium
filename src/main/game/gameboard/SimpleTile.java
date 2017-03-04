package game.gameboard;

import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.entities.Army;
import game.entities.RallyPoint;

import java.util.ArrayList;
public class SimpleTile{
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Resource food;
    private Resource ore;
    private Resource peat;
    TileVisibilityEnum visibility = TileVisibilityEnum.INVISIBLE;

    SimpleTile(Tile tile) {
		units = tile.getUnits();
		armies = tile.getArmies();
		rallyPoints = tile.getRallyPoints();
		structure = tile.getStructure();
		food = tile.getResource(ResourceTypeEnum.FOOD);
		ore = tile.getResource(ResourceTypeEnum.ORE);
		peat = tile.getResource(ResourceTypeEnum.PEAT);
    }

    public void updateTile(Tile tile) {
    	if (visibility == TileVisibilityEnum.VISIBLE) {
    		units = tile.getUnits();
    		armies = tile.getArmies();
    		rallyPoints = tile.getRallyPoints();
    		structure = tile.getStructure();
    		food = tile.getResource(ResourceTypeEnum.FOOD);
    		ore = tile.getResource(ResourceTypeEnum.ORE);
    		peat = tile.getResource(ResourceTypeEnum.PEAT);
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