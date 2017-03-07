package view.game.drawers;

import java.awt.Point;
import game.gameboard.TerrainEnum;
import game.gameboard.TileVisibilityEnum;
import javafx.scene.image.Image;
import view.Animation;
import view.GameModelAdapter;
import view.assets.AssetManager;
import view.game.GamePanel;

public class TileDrawer {
    private GamePanel gamePanel;
    Animation grassAnimation;
    Animation waterAnimation;
    Animation mountainAnimation;
    Animation invisibleAnimation;
    Animation powerPlant;
    
    public TileDrawer(GamePanel gamePanel, GameModelAdapter gameModelAdapter, AssetManager assetManager) {
        this.gamePanel = gamePanel;
        grassAnimation = new Animation(new Image[] {  assetManager.getImage("TERRAIN_GRASS1"),
												 	  assetManager.getImage("TERRAIN_GRASS2"), 
													  assetManager.getImage("TERRAIN_GRASS3")}, 30);
        waterAnimation = new Animation(new Image[] { assetManager.getImage("TERRAIN_WATER1"),
													assetManager.getImage("TERRAIN_WATER2"), 
													assetManager.getImage("TERRAIN_WATER3"),
													assetManager.getImage("TERRAIN_WATER4"),
													assetManager.getImage("TERRAIN_WATER5"),
													assetManager.getImage("TERRAIN_WATER6")}, 20);
        mountainAnimation = new Animation(new Image[] { assetManager.getImage("TERRAIN_MOUNTAIN1"),
														assetManager.getImage("TERRAIN_MOUNTAIN2"), 
														assetManager.getImage("TERRAIN_MOUNTAIN3")}, 25);
        invisibleAnimation = new Animation(new Image[] { assetManager.getImage("TILE_INVISIBLE1"),
														assetManager.getImage("TILE_INVISIBLE2"), 
														assetManager.getImage("TILE_INVISIBLE3")}, 100);
    }

    public void drawTile(Point p, TerrainEnum type, TileVisibilityEnum visibility) {
    	if (visibility == TileVisibilityEnum.INVISIBLE) {
    		gamePanel.drawAnimatedTileElement(p, invisibleAnimation);
    	} else {
            switch (type) {
            case GRASS:
            	gamePanel.drawAnimatedTileElement(p, grassAnimation);
                break;
            case SAND:
                gamePanel.drawStaticTileElement(p, "TERRAIN_SAND");
                break;
            case WATER:
                gamePanel.drawAnimatedTileElement(p, waterAnimation);
                break;
            case MOUNTAIN:
                gamePanel.drawAnimatedTileElement(p, mountainAnimation);
                break;
            case NON_TILE:
                break;
            }
    		if (visibility == TileVisibilityEnum.SEMI_VISIBLE) {
    			gamePanel.drawStaticTileElement(p, "TILE_SEMI_VISIBLE");
    		}
    	}
    }

    protected void drawMovingTiles() {
    	/*
        for (Location moveLocation : game.getMoveLocations()) {
        	if (game.getGameBoard().getTiles()[moveLocation.getX()][moveLocation.getY()].getTileType() != TerrainEnum.INVISIBLE) {
                gamePanel.drawStaticTileElement(new Point(moveLocation.getX(), moveLocation.getY()), "MOVE_SELECTED");
            }
        }
        */
    }
}
