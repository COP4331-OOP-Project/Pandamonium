package view;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.assets.AssetManager;

public abstract class Panel {
	private ViewEnum viewEnum;
	private AssetManager assets;
	private GameModelAdapter gameModelAdapter;
    private boolean isVisible = true;
    private boolean GUIVisible = false;
    
    public abstract void draw(GraphicsContext g, Point screenDimensions, long currentPulse);

    public abstract void hideGUIElements();
    
    public abstract void showGUIElements();
    
    public Panel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum viewEnum) {
    	this.gameModelAdapter = gameModelAdapter;
    	this.assets = assets;
    	this.viewEnum = viewEnum;
    }
    
    public void drawPanel(GraphicsContext g, Point screenDimensions, ViewEnum gameMode, long currentPulse) {
    	if (gameMode == viewEnum && isVisible) {
    		draw(g, screenDimensions, currentPulse);
    		if (!GUIVisible) {
    			GUIVisible = true;
    			showGUIElements();
    		}
    	} else {
    		if (GUIVisible) {
    			GUIVisible = false;
    			hideGUIElements();
    		}
    	}
    }
    
    public void setIsVisible(boolean isVisible) {
    	this.isVisible = isVisible;
    }
    
    public boolean getIsVisible() {
    	return isVisible;
    }
    
    public AssetManager getAssets() {
    	return assets;
    }
    
    public GameModelAdapter getAdapter() {
    	return gameModelAdapter;
    }
}