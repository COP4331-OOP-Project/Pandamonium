package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    protected void drawBase(Point p, int player, int rotation) {
        switch (player) {
            case 0:
                gamePanel.drawStaticTileElement(p, "BASE_O");
                break;
            case 1:
                gamePanel.drawStaticTileElement(p, "BASE_B");
                break;
            case 2:
                gamePanel.drawStaticTileElement(p, "BASE_Y");
                break;
            case 3:
                gamePanel.drawStaticTileElement(p, "BASE_G");
                break;
            default:
                log.warn("Invalid player specific for drawing base");
        }
        gamePanel.drawStaticTileElement(p, rotation, "BASE_ARROW");
    }
}
