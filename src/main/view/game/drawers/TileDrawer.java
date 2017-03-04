package view.game.drawers;

import java.awt.Point;

import game.GameModel;
import game.gameboard.Location;
import game.gameboard.TerrainEnum;
import javafx.scene.image.Image;
import view.Animation;
import view.GameModelAdapter;
import view.assets.AssetManager;
import view.game.GamePanel;

public class TileDrawer {
	private AssetManager assetManager;
	private GameModelAdapter game;
    private GamePanel gamePanel;
    Animation grassAnimation;
    Animation waterAnimation;
    Animation mountainAnimation;

    public TileDrawer(GamePanel gamePanel, GameModelAdapter gameModelAdapter, AssetManager assetManager) {
        this.gamePanel = gamePanel;
        this.assetManager = assetManager;
        grassAnimation = new Animation(new Image[] {  assetManager.getImage("TERRAIN_GRASS1"),
												 	  assetManager.getImage("TERRAIN_GRASS2"), 
													  assetManager.getImage("TERRAIN_GRASS3")}, 30);
        waterAnimation = new Animation(new Image[] { assetManager.getImage("TERRAIN_WATER3"),
													assetManager.getImage("TERRAIN_WATER2"), 
													assetManager.getImage("TERRAIN_WATER1")}, 25);
        mountainAnimation = new Animation(new Image[] { assetManager.getImage("TERRAIN_MOUNTAIN1"),
														assetManager.getImage("TERRAIN_MOUNTAIN2"), 
														assetManager.getImage("TERRAIN_MOUNTAIN3")}, 15);
        game = gameModelAdapter;
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
            case INVISIBLE:
                break;
        }
    }

    protected void drawMovingTiles() {
        //for (Location moveLocation : game.getMoveLocations()) {
            /*
        	if (game.getGameBoard().getTiles()[moveLocation.getX()][moveLocation.getY()].getTileType() != TerrainEnum.INVISIBLE) {
                gamePanel.drawStaticTileElement(new Point(moveLocation.getX(), moveLocation.getY()), "MOVE_SELECTED");
            }
            */
        //}
    }
}
