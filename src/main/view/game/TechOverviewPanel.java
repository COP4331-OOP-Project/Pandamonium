package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class TechOverviewPanel extends OverviewPanel{

	public TechOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        drawPanelBox(g, screenDimensions);
        g.setFont(getAssets().getFont(2));
        g.fillText("Tech Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
	}

	public void hideGUIElements() {

	}

	public void showGUIElements() {

	}
}
