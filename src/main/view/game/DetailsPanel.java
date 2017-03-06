package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public abstract class DetailsPanel extends Panel{
	
    private int guiPanelHeight =
            (int) getAssets().getImage("GUI_BOTTOM").getHeight();
	
	public DetailsPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
	}

    void drawBar(GraphicsContext g, Point screenDimensions) {
        g.drawImage(getAssets().getImage("GUI_BOTTOM"), 0, screenDimensions.y - guiPanelHeight);
    }
}
