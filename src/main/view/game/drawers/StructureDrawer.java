package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.game.GamePanel;

public class StructureDrawer {

    private final static Logger log = LogManager.getLogger(StructureDrawer.class);

    GamePanel gamePanel;

    public StructureDrawer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
