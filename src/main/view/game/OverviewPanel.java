package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public abstract class OverviewPanel extends Panel {
    public OverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
		setIsVisible(false);
	}

	public void drawPanelBox(GraphicsContext g, Point screenDimensions) {
        g.drawImage(getAssets().getImage("DETAILS_PANEL"),
                185, 50, screenDimensions.x - 259, screenDimensions.y - 147);
    }
	
	public void hideIfVisible() {
		if (getIsVisible()) {
			setIsVisible(false);
		}
	}
}
