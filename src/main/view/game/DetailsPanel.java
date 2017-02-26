package view.game;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import view.Assets;
import view.Panel;
import view.ViewEnum;

public abstract class DetailsPanel extends Panel{
	
    private int guiPanelHeight =
            (int) getAssets().getImage("GUI_BOTTOM").getHeight();
	
	public DetailsPanel(Assets assets, ViewEnum view) {
		super(assets, view);
	}

    void drawBar(GraphicsContext gc, Point screenDimensions) {
        gc.drawImage(getAssets().getImage("GUI_BOTTOM"), 0, screenDimensions.y - guiPanelHeight);
    }
}
