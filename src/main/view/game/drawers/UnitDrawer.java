package view.game.drawers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.game.GamePanel;

public class UnitDrawer {
    private final static Logger log = LogManager.getLogger(UnitDrawer.class);
    private GamePanel gamePanel;

    public UnitDrawer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
/*
    protected void drawUnit(Point p, UnitEnum type, int player,
                            int rotation) {
        switch (player) {
            case 0:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_O");
                break;
            case 1:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_B");
                break;
            case 2:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_Y");
                break;
            case 3:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_G");
                break;
            default:
                log.warn("Invalid Player :" + player
                        + " cannot have units drawn");
        }

        switch (type) {
            case MELEE:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_MELEE");
                break;
            case RANGED:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_RANGED");
                break;
            case EXPLORER:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_EXPLORER");
                break;
            case COLONIST:
                gamePanel.drawStaticTileElement(p, rotation, "UNIT_COLONIST");
                break;
            default:
                log.warn("Invalid unit Type :" + type
                        + " cannot be drawn");
        }
        
    }
    */
}
