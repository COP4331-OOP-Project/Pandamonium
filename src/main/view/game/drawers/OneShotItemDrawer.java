package view.game.drawers;

import game.gameboard.areaEffects.AreaEffectDecalEnum;
import view.assets.AssetManager;
import view.game.GamePanel;

import java.awt.*;

public class OneShotItemDrawer {
    private GamePanel gamePanel;

    public OneShotItemDrawer(GamePanel gamePanel, AssetManager assetManager) {
        this.gamePanel = gamePanel;
    }

    public void drawOneShotItem(Point p, AreaEffectDecalEnum areaEffectDecalEnum) {
        switch (areaEffectDecalEnum) {
            case DAMAGE:
                gamePanel.drawStaticTileElement(p, "ITEM_LOSE");
                break;
            case HEAL:
                gamePanel.drawStaticTileElement(p, "ITEM_HEAL");
                break;
            case INSTANT_DEATH:
                gamePanel.drawStaticTileElement(p, "ITEM_DIE");
                break;
        }
    }
}
