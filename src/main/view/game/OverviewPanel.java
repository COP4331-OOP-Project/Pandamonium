package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public abstract class OverviewPanel extends Panel {
    public OverviewPanel(AssetManager assets, ViewEnum view) {
		super(assets, view);
	}

	public void drawPanelBox(GraphicsContext gc, Point screenDimensions) {
        gc.drawImage(getAssets().getImage("DETAILS_PANEL"),
                screenDimensions.x / 2 - 400, screenDimensions.y / 2 - 300);
    }
}
