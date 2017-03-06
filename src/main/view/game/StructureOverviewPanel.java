package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class StructureOverviewPanel extends OverviewPanel {
    public StructureOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
		setIsVisible(false);
	}

    @Override
    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        drawPanelBox(g, screenDimensions);
        Font oldFont = g.getFont();
        g.setFont(getAssets().getFont(2));
        g.fillText("Structure Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
        g.setFont(getAssets().getFont(1));
        //for (int i = 0; i < game.getCurrentPlayer().getBaseCount(); i++) {
        //    g.fillText("Base", screenDimensions.x / 2 - 370, screenDimensions.y / 2 + (i * 30) - 200);
        //}
        g.setFont(oldFont);
    }

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}
