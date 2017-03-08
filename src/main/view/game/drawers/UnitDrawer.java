package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
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

	public void drawUnit(Point p, EntityId entityId, EntitySubtypeEnum type) {
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
			case RANGE:
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
