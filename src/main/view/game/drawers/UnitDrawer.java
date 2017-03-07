package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.EntityId;
import game.entities.units.UnitType;
import view.GameModelAdapter;
import view.assets.AssetManager;
import view.game.GamePanel;

public class UnitDrawer {
    private final static Logger log = LogManager.getLogger(UnitDrawer.class);
    private GamePanel gamePanel;
    private GameModelAdapter gameModelAdapter;
    private AssetManager assets;
    
    public UnitDrawer(GamePanel gamePanel, GameModelAdapter gameModelAdapter, AssetManager assets) {
        this.gamePanel = gamePanel;
        this.gameModelAdapter = gameModelAdapter;
        this.assets = assets;
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

	public void drawUnit(Point p, EntityId entityId, UnitType type) {
		if (entityId.getPlayerId() == 0) {
			drawHuman(p);
		} else {
			drawPanda(p);
		}
		switch (type) {
			case COLONIST:
				gamePanel.drawStaticTileElement(p, "UNIT_COLONIST");
			case EXPLORER:
				gamePanel.drawStaticTileElement(p, "UNIT_EXPLORER");
			case MELEE:
				gamePanel.drawStaticTileElement(p, "UNIT_MELEE");
			case RANGED:
				gamePanel.drawStaticTileElement(p, "UNIT_RANGED");
		}	
	}
	
	private void drawPanda(Point p) {
		 gamePanel.drawStaticTileElement(p, "UNIT_SMALL_PANDA");
	}

	private void drawHuman(Point p) {
		 gamePanel.drawStaticTileElement(p, "UNIT_SMALL_HUMAN");
	}
}
