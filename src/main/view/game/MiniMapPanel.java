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
    
    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        this.screenDimensions = screenDimensions;
        drawBackground(g);
        SimpleTile[][] tiles = getAdapter().getCurrentTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
            	Point loc = new Point(i, j);
            	SimpleTile tile = tiles[i][j];
                drawSmallTile(g, loc, tile.getTileType(), tile.getVisibility());
            }
        }
        drawBorder(g);
    }

	private void drawSmallStructure(Point tileLoc, int ownerID, GraphicsContext g) {
        if (ownerID == 0) {
            g.drawImage(getImage("BASE_HUMAN_MM"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);

        } else {
            g.drawImage(getImage("BASE_PANDA_MM"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);
        }
    }

    private void drawSmallUnit(Point tileLoc, int ownerID, GraphicsContext g) {
        if (ownerID == 0) {
            g.drawImage(getImage("UNIT_HUMAN_MM"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);

        } else {
            g.drawImage(getImage("UNIT_PANDA_MM"), offsetMini(tileLoc).x, offsetMini(tileLoc).y);
        }
    }


    private void drawBackground(GraphicsContext g) {
        g.drawImage(getImage("GUI_MINI_MAP_BACKGROUND"), screenDimensions.x - DISTANCE_FROM_RIGHT - 27
                , screenDimensions.y - DISTANCE_FROM_BOTTOM + 182);
	}
    
    private void drawBorder(GraphicsContext g) {
        g.drawImage(getImage("GUI_MINI_MAP_BORDER"), screenDimensions.x - DISTANCE_FROM_RIGHT - 27
                , screenDimensions.y - DISTANCE_FROM_BOTTOM + 182);

    }

    private void drawSmallTile(GraphicsContext g, Point tileLoc, TerrainEnum tileType, TileVisibilityEnum visibility) {
    	if (visibility == TileVisibilityEnum.VISIBLE || visibility == TileVisibilityEnum.SEMI_VISIBLE) {
	        switch (tileType) {
	            case GRASS:
	                g.drawImage(getImage("GRASS_MINI"),
	                        offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case SAND:
	                g.drawImage(getImage("SAND_MINI"),
	                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case WATER:
	                g.drawImage(getImage("WATER_MINI"),
	                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case MOUNTAIN:
	                g.drawImage(getImage("MOUNTAIN_MINI"),
	                		offsetMini(tileLoc).x, offsetMini(tileLoc).y);
	                break;
	            case NON_TILE:
	                break;
	            default:
	                log.warn("Invalid tile type on minimap: " + tileType);
	        }
    	} else if (visibility == TileVisibilityEnum.INVISIBLE) {
    		g.drawImage(getImage("CLOUDS_MINI"),
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
