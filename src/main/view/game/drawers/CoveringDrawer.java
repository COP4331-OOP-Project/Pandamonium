package view.game.drawers;

import java.awt.Point;

import game.gameboard.TerrainEnum;
import game.gameboard.TileVisibilityEnum;
import javafx.scene.image.Image;
import view.Animation;
import view.assets.AssetManager;
import view.game.GamePanel;

public class CoveringDrawer {
    Animation fogAnimation;
    GamePanel gamePanel;
    
    public CoveringDrawer(GamePanel gamePanel, AssetManager assetManager) {
    this.gamePanel = gamePanel;
    fogAnimation = new Animation(new Image[] { assetManager.getImage("TILE_FOG1"),
											   assetManager.getImage("TILE_FOG2"), 
											   assetManager.getImage("TILE_FOG3")}, 100);
    
    }
    
    public void drawCovering(Point p, TerrainEnum type, TileVisibilityEnum visibility) {
    	switch (visibility) {
			case INVISIBLE :
				gamePanel.drawAnimatedTileElement(p, fogAnimation);
				break;
			case SEMI_VISIBLE :
				gamePanel.drawStaticTileElement(p, "TILE_SEMI_VISIBLE");
				break;
			default :
				break;
		}
    }
}
