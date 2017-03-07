package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.assets.AssetManager;
import view.game.Camera;
import view.game.GamePanel;

public class ArmyDrawer {

    private final static Logger log = LogManager.getLogger(GamePanel.class);
    private Font armyFont;
    private GamePanel gamePanel;
    private GameModelAdapter gameModelAdapter;
    private AssetManager assets;
    
    public ArmyDrawer(GamePanel gamePanel, GameModelAdapter gameModelAdapter, AssetManager assets) {
        armyFont = assets.getFont(0);
    	this.gamePanel = gamePanel;
    	this.gameModelAdapter = gameModelAdapter;
    	this.assets = assets;
    }

    public void drawArmy(Point p, int player, int rotation,
                            int numOfUnits) {
        switch (player) {
            case 0:
                gamePanel.drawStaticTileElement(p, rotation, "ARMY_O");
                break;
            case 1:
                gamePanel.drawStaticTileElement(p, rotation, "ARMY_B");
                break;
            default:
                log.warn("Invalid Player :" + player
                        + " cannot have units drawn");
        }
        GraphicsContext g = gamePanel.getGC();
        Camera camera = gamePanel.getCamera();
        g.setFont(armyFont);

        if (numOfUnits < 10) {
            g.setFill(Color.BLACK);
            g.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 15, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 18);
            g.setFill(Color.WHITE);
            g.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 17, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 17);
        } else {
            g.setFill(Color.BLACK);
            g.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 23, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 18);
            g.setFill(Color.WHITE);
            g.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 25, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 17);
        }
    }
}
