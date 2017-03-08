package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class UnitOverviewPanel extends OverviewPanel {

	public UnitOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets,
			ViewEnum viewEnum) {
		super(gameModelAdapter, assets, viewEnum);
		setIsVisible(false);
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		drawPanelBox(g, screenDimensions);
		Font oldFont = g.getFont();
		g.setFont(getAssets().getFont(2));
		g.fillText("Unit Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
		g.setFont(getAssets().getFont(1));
	}

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}
