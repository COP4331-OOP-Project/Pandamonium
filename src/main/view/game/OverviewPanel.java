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
        g.drawImage(getImage("DETAILS_PANEL"),
                screenDimensions.x / 2 - 400, screenDimensions.y / 2 - 300);
    }
	
	public void hideIfVisible() {
		if (getIsVisible()) {
			setIsVisible(false);
		}
	}
}
