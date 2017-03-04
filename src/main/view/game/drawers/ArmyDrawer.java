package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.game.Camera;
import view.game.GamePanel;

public class ArmyDrawer {

    private final static Logger log = LogManager.getLogger(GamePanel.class);
    Font armyFont = new Font("Lucida Sans", 40);

    private GamePanel gamePanel;

    public ArmyDrawer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
            case 2:
                gamePanel.drawStaticTileElement(p, rotation, "ARMY_Y");
                break;
            case 3:
                gamePanel.drawStaticTileElement(p, rotation, "ARMY_G");
                break;
            default:
                log.warn("Invalid Player :" + player
                        + " cannot have units drawn");
        }
        GraphicsContext gc = gamePanel.getgc();
        Camera camera = gamePanel.getCamera();
        gc.setFont(armyFont);

        if (numOfUnits < 10) {
            gc.setFill(Color.BLACK);
            gc.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 15, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 18);
            gc.setFill(Color.WHITE);
            gc.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 17, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 17);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 23, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 18);
            gc.setFill(Color.WHITE);
            gc.fillText("" + numOfUnits, camera.offset(p).x +
                    gamePanel.getTileSize() / 2 - 25, camera.offset(p).y +
                    gamePanel.getTileSize() / 2 + 17);
        }
    }
}
