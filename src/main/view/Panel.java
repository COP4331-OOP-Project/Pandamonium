package view;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import view.Assets;

public abstract class Panel {
	private ViewEnum view;
	private Assets assets;
    private boolean isVisible = true;
    
    public abstract void draw(GraphicsContext gc, Point screenDimensions);

    public abstract void hideGUIElements();
    
    public abstract void showGUIElements();
    
    public Panel(Assets assets, ViewEnum view) {
    	this.assets = assets;
    }
    
    public void drawPanel(GraphicsContext gc, Point screenDimensions) {
        if (isVisible) {
            draw(gc, screenDimensions);
        }
    }
    
    public void setIsVisible(boolean isVisible) {
    	if (isVisible) {
    		showGUIElements();
    	} else {
    		hideGUIElements();
    	}
    	this.isVisible = isVisible;
    }
    
    public boolean getIsVisible() {
    	return isVisible;
    }
    
    public Assets getAssets() {
    	return assets;
    }
}