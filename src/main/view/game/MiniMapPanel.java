package view.game;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.gameboard.SimpleTile;
import game.gameboard.TerrainEnum;
import game.gameboard.TileVisibilityEnum;
import javafx.scene.canvas.GraphicsContext;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public class MiniMapPanel extends Panel {
   

	private static final int HEX_SIZE = 12;
    private static final int HEX_HEIGHT = 10;
    private static final int DISTANCE_FROM_RIGHT = 390;
    private static final int DISTANCE_FROM_BOTTOM = 427;
    private final static Logger log = LogManager.getLogger(MiniMapPanel.class);
    private Point screenDimensions;

    public MiniMapPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
	}
    
    public void draw(GraphicsContext gc, Point screenDimensions, long currentPulse) {
        this.screenDimensions = screenDimensions;
        SimpleTile[][] tiles = getAdapter().getCurrentTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
            	Point loc = new Point(i, j);
            	SimpleTile tile = tiles[i][j];
                drawSmallTile(gc, loc, tile.getTileType(), tile.getVisibility());
            }
        }
        drawBorder(gc);
    }

    private void drawSmallStructure(Point tileLoc, int ownerID, GraphicsContext gc) {
        if (ownerID == 0) {
            gc.drawImage(getAssets().getImage("BASE_O_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);

        } else {
            gc.drawImage(getAssets().getImage("BASE_B_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);
        }
    }

    private void drawSmallUnit(Point tileLoc, int ownerID, GraphicsContext gc) {
        if (ownerID == 0) {
            gc.drawImage(getAssets().getImage("UNIT_O_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);

        } else {
            gc.drawImage(getAssets().getImage("UNIT_B_SMALL"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);
        }
    }

    private void drawBorder(GraphicsContext gc) {
        gc.drawImage(getAssets().getImage("GUI_MINI_MAP_BORDER"), screenDimensions.x - DISTANCE_FROM_RIGHT - 27
                , screenDimensions.y - DISTANCE_FROM_BOTTOM + 182);

    }

    private void drawSmallTile(GraphicsContext gc, Point tileLoc, TerrainEnum tileType, TileVisibilityEnum visibility) {
    	if (visibility == TileVisibilityEnum.VISIBLE || visibility == TileVisibilityEnum.SEMI_VISIBLE) {
	        switch (tileType) {
	            case GRASS:
	                gc.drawImage(getAssets().getImage("GRASS_MINI"),
	                        offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case SAND:
	                gc.drawImage(getAssets().getImage("SAND_MINI"),
	                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case WATER:
	                gc.drawImage(getAssets().getImage("WATER_MINI"),
	                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case MOUNTAIN:
	                gc.drawImage(getAssets().getImage("MOUNTAIN_MINI"),
	                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case NON_TILE:
	                break;
	            default:
	                log.warn("Invalid tile type on minimap: " + tileType);
	        }
    	} else if (visibility == TileVisibilityEnum.INVISIBLE) {
    		gc.drawImage(getAssets().getImage("CLOUDS_MINI"),
                    offsetMini(tileLoc).x, offsetMini(tileLoc).y);
    	}
    }

    private Point offsetMini(Point p) {
        Point offsetPoint = new Point();
        offsetPoint.x = screenDimensions.x + ((int) (((p.x * 0.5f) * HEX_SIZE / 2) + (p.x * 0.5f) * HEX_SIZE)) -
                DISTANCE_FROM_RIGHT;
        offsetPoint.y = ((int) ((p.y * HEX_HEIGHT) + ((p.x * 0.5f) * HEX_HEIGHT))) + screenDimensions.y -
                DISTANCE_FROM_BOTTOM;
        return offsetPoint;
    }

	public void hideGUIElements() {
		
	}

	public void showGUIElements() {
	}
}
