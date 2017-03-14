package view.game.drawers;

import game.gameboard.TerrainEnum;
import javafx.scene.image.Image;
import view.Animation;
import view.assets.AssetManager;
import view.game.GamePanel;

import java.awt.*;

public class TileDrawer {
    private GamePanel gamePanel;
    private Animation grassAnimation;
    private Animation waterAnimation;
    private Animation mountainAnimation;
    public TileDrawer(GamePanel gamePanel, AssetManager assetManager) {
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
    }

    public void drawTile(Point p, TerrainEnum type) {
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
