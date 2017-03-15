package game.gameboard;

import game.entities.Army;
import game.entities.BattleGroup;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.areaEffects.AreaEffect;
import game.gameboard.oneShotItem.OneShotItem;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private boolean resourcesProspected = false;
    private TileVisibilityEnum visibility = TileVisibilityEnum.INVISIBLE;
    private final static Logger log = LogManager.getLogger(SimpleTile.class);
    private AreaEffect areaEffect;
    private OneShotItem oneShotItem;

    public SimpleTile(Tile tile) {
    	if (tile.getTileType() == TerrainEnum.NON_TILE)
    		visibility = TileVisibilityEnum.NON_TILE;
    	Terrain = tile.getTileType();
    	units = new ArrayList<>();
    	units.addAll(tile.getUnits());
    	armies = new ArrayList<>();
    	armies.addAll(tile.getArmies());
    	rallyPoints = new ArrayList<>();
    	rallyPoints.addAll(tile.getRallyPoints());
		structure = tile.getStructure();
		food = tile.getResource(ResourceTypeEnum.FOOD);
		ore = tile.getResource(ResourceTypeEnum.ORE);
		peat = tile.getResource(ResourceTypeEnum.PEAT);
		areaEffect = tile.getAreaEffect();
		oneShotItem = tile.getOneShotItem();
    }

    public void updateTile(Tile tile) {
    	if (visibility == TileVisibilityEnum.VISIBLE) {
			units = new ArrayList<>();
			units.addAll(tile.getUnits());
			armies = new ArrayList<>();
			armies.addAll(tile.getArmies());
			rallyPoints = new ArrayList<>();
			rallyPoints.addAll(tile.getRallyPoints());
    		structure = tile.getStructure();
    		food = tile.getResource(ResourceTypeEnum.FOOD);
    		ore = tile.getResource(ResourceTypeEnum.ORE);
    		peat = tile.getResource(ResourceTypeEnum.PEAT);
    		areaEffect = tile.getAreaEffect();
    		oneShotItem = tile.getOneShotItem();
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
	
	public Resource getFood() {
		return food;
	}
	
	public Resource getOre() {
		return ore;
	}
	
	public Resource getPeat() {
		return peat;
	}

	public AreaEffect getAreaEffect() { return areaEffect; }

	public OneShotItem getOneShotItem() { return oneShotItem; }
	
	public boolean getResourcesProspected() {
		return resourcesProspected;
	}
}