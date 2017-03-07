package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.structures.StructureType;
import javafx.scene.image.Image;
import view.Animation;
import view.GameModelAdapter;
import view.assets.AssetManager;
import view.game.GamePanel;

public class StructureDrawer {
    private final static Logger log = LogManager.getLogger(StructureDrawer.class);
    GamePanel gamePanel;
    GameModelAdapter gameModelAdapter;
    AssetManager assetManager;
    Animation powerPlantPanda;
    Animation powerPlantHuman;


    public StructureDrawer(GamePanel gamePanel, GameModelAdapter gameModelAdapter, AssetManager assetManager) {
        this.gamePanel = gamePanel;
        this.gameModelAdapter = gameModelAdapter;
        this.assetManager = assetManager;
        powerPlantHuman = new Animation(new Image[] { assetManager.getImage("POWER_PLANT_HUMAN1"),
   			 										  assetManager.getImage("POWER_PLANT_HUMAN2"), 
   			 										  assetManager.getImage("POWER_PLANT_HUMAN3")}, 50);
        powerPlantPanda = new Animation(new Image[] { assetManager.getImage("POWER_PLANT_PANDA1"),
   			 										  assetManager.getImage("POWER_PLANT_PANDA2"), 
   			 										  assetManager.getImage("POWER_PLANT_PANDA3")}, 50);
    }

    public void drawStructure(Point p, int player, StructureType type) {
        switch (type) {
        case CAPITOL :
        	if (player == 0)
        		gamePanel.drawStaticTileElement(p, "BASE_HUMAN");
        	else
        		gamePanel.drawStaticTileElement(p, "BASE_PANDA");
        	break;
        case PLANT :
        	if (player == 0)
        		gamePanel.drawAnimatedTileElement(p, powerPlantHuman);
        	else
        		gamePanel.drawAnimatedTileElement(p, powerPlantPanda);
        	break;
        case OBSERVE:
        	if (player == 0)
        		gamePanel.drawStaticTileElement(p, "OBSERVATION_TOWER_HUMAN");
        	else
        		gamePanel.drawStaticTileElement(p, "OBSERVATION_TOWER_PANDA");
        	break;
		default:
			break;
        }
    }
}
