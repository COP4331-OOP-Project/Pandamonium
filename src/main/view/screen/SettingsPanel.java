package view.screen;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import view.Panel;
import view.PanelManager;
import view.ViewEnum;
import view.assets.AssetManager;

public class SettingsPanel extends Panel{
	PanelManager panelManager;
	
	public SettingsPanel(Group root, PanelManager panelManager, AssetManager assets, ViewEnum viewEnum) {
		super(assets, viewEnum);
		this.panelManager = panelManager;
	}

	@Override
	public void draw(GraphicsContext gc, Point screenDimensions) {
	}

	@Override
	public void hideGUIElements() {
	}

	@Override
	public void showGUIElements() {
	}

}
