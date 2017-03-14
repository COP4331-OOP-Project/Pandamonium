package view.game.drawers;

import game.gameboard.areaEffects.AreaEffectDecalEnum;
import view.assets.AssetManager;
import view.game.GamePanel;

import java.awt.*;

public class AreaEffectDrawer {
    private GamePanel gamePanel;

    public AreaEffectDrawer(GamePanel gamePanel, AssetManager assetManager) {
        this.gamePanel = gamePanel;
    }

    public void drawAreaEffect(Point p, AreaEffectDecalEnum areaEffectDecalEnum) {
        switch (areaEffectDecalEnum) {
            case DAMAGE:
                gamePanel.drawStaticTileElement(p, "AOE_LOSE");
                break;
            case HEAL:
                gamePanel.drawStaticTileElement(p, "AOE_HEAL");
                break;
            case INSTANT_DEATH:
                gamePanel.drawStaticTileElement(p, "AOE_DIE");
                break;
        }
    }
}
