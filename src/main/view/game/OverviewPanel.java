package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.Assets;
import view.Panel;
import view.ViewEnum;

public abstract class OverviewPanel extends Panel {
    public OverviewPanel(Assets assets, ViewEnum view) {
		super(assets, view);
	}

	public void drawPanelBox(GraphicsContext gc, Point screenDimensions) {
        gc.drawImage(getAssets().getImage("DETAILS_PANEL"),
                screenDimensions.x / 2 - 400, screenDimensions.y / 2 - 300);
    }
}
