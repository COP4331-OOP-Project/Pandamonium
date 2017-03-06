package game.gameboard;

import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.entities.Army;
import game.entities.RallyPoint;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SimpleTile{
    private TerrainEnum Terrain;
    private ArrayList<Unit> units;
    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;
    private Structure structure;
    private Resource food;
    private Resource ore;
    private Resource peat;
    private boolean resourcesProspected = false;
    private TileVisibilityEnum visibility = TileVisibilityEnum.INVISIBLE;
    private final static Logger log = LogManager.getLogger(SimpleTile.class);

    public SimpleTile(Tile tile) {
    	if (tile.getTileType() == TerrainEnum.NON_TILE)
    		visibility = TileVisibilityEnum.NON_TILE;
    	Terrain = tile.getTileType();
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
    
    public int getUnitCount() {
    	return units.size();
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
    	if (visibility == TileVisibilityEnum.INVISIBLE && this.visibility != TileVisibilityEnum.INVISIBLE) {
    		log.error("Can't set visible or semivisible tile to invisible!");
    	} else if (visibility == TileVisibilityEnum.NON_TILE || visibility == TileVisibilityEnum.NON_TILE) {
    		log.error("Can't modify non-tile status");
    	} else {
        	this.visibility = visibility;
    	}
    }

	public void setSemiIfVisible() {
		if (visibility == TileVisibilityEnum.VISIBLE) {
			setVisibility(TileVisibilityEnum.SEMI_VISIBLE);
		}
	}

	public void setVisible() {
		setVisibility(TileVisibilityEnum.VISIBLE);
	}
	
	public void setProspected() {
		resourcesProspected = true;
	}
}